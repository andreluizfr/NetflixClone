package com.example.ApiGateway.Configuration.Filters;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ApiGateway.Authentication.Business.TokenService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AddHeadersInResponseFilter extends ZuulFilter {

	@Autowired
	TokenService tokenService;

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 98;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();

		addHeadersToResponseContext(ctx);

		return null;
	}

	private void addHeadersToResponseContext(RequestContext ctx) {

		ctx.addZuulResponseHeader("X-Foo", UUID.randomUUID().toString());
		ctx.addZuulResponseHeader("X-Start-Date", ctx.getZuulRequestHeaders().get("X-Start-Date".toLowerCase()));
		ctx.addZuulResponseHeader("X-End-Date", String.valueOf(new Date()));
		//remover o que está vindo do microsserviço, porque será reescrito no bean do cors
		ctx.getZuulResponseHeaders().removeIf(ssp -> ssp.first().toLowerCase().startsWith("access-control-allow"));
		ctx.getRequest().getCookies(); 
	}

}
