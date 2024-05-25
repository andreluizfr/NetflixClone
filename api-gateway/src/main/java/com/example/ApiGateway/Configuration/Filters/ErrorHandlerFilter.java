package com.example.ApiGateway.Configuration.Filters;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.ApiGateway.Util.ResponseHandler;
import com.netflix.client.ClientException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ErrorHandlerFilter extends ZuulFilter {

	protected static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return -1; // to run before SendErrorFilter
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		// only forward to errorPath if it hasn't been forwarded to already
		return (ctx.getThrowable() != null && !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false));
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.set(SEND_ERROR_FILTER_RAN); // will block the SendErrorFilter from running

		Throwable throwable = ctx.getThrowable();
		// ribbon didnt find server or hystrix didnt find server
		if (throwable.getCause().getCause().getCause() instanceof ClientException
				|| throwable.getCause().getCause().getCause() instanceof HystrixRuntimeException) {

			ctx.getResponse().setStatus(HttpStatus.SC_SERVICE_UNAVAILABLE);

			String updatedResponse = ResponseHandler
					.generateBody("Sem servidor disponível. Mensagem enviada do Api Gateway", null);

			try {
				OutputStream outStream = ctx.getResponse().getOutputStream();
				outStream.write(updatedResponse.getBytes(), 0, updatedResponse.length());
				outStream.flush();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throwable.printStackTrace();

			ctx.getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);

			String updatedResponse = ResponseHandler.generateBody("Erro desconhecido. Mensagem enviada do Api Gateway",
					null);

			try {
				OutputStream outStream = ctx.getResponse().getOutputStream();
				outStream.write(updatedResponse.getBytes(), 0, updatedResponse.length());
				outStream.flush();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Impede a execução dos outros filtros de erro
		ctx.setSendZuulResponse(false);

		return null;
	}

}
