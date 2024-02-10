package com.example.ApiGateway.Filters;

import java.util.stream.Collectors;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class LogResponsePostFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 99;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();

		System.out.println(
				"\n" +
				"In zuul after PosFilter" + "\n" +
				"Response Status : " + ctx.getResponseStatusCode() + "\n" +
				"Response URL : " + ctx.getRequest().getRequestURL().toString() + "\n" +
				"Response Headers Key Set: " + ctx.getZuulResponseHeaders().stream().map(ssp -> ssp.first()).collect(Collectors.toList()) + "\n" +
				"Response Headers Values Set: " + ctx.getZuulResponseHeaders().stream().map(ssp -> ssp.second()).collect(Collectors.toList()) + "\n" +
				"Response Body: " + ctx.getResponseBody() +
				"\n"
		);

		return null;
	}

}
