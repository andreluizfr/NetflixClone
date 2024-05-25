package com.example.ApiGateway.Configuration.Filters;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class LogRequestPreFilter extends ZuulFilter {

	private static final Logger logger = LogManager.getLogger(LogRequestPreFilter.class);
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 999;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		String requestBody;
		try {
			requestBody = new String(request.getInputStream().readAllBytes());
		} catch (IOException | RuntimeException e) {
			requestBody = "";
		}

		logger.info(
				"\n" +
				"In zuul after PreFilter" + "\n" +
				"Request Method : " + request.getMethod() + "\n" +
				"Request URL : " + request.getRequestURL().toString() + "\n" +
				"Request Query: " + request.getQueryString() + "\n" +
				"Request Headers Key Set: " + ctx.getZuulRequestHeaders().keySet().toString() + "\n" +
				"Request Headers Values Set: " + ctx.getZuulRequestHeaders().values().toString() + "\n" +
				"Request Body: " + requestBody +
				"\n");

		return null;
	}
}
