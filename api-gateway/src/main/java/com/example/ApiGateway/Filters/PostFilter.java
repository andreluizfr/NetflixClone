package com.example.ApiGateway.Filters;

import java.util.UUID;
import java.util.stream.Collectors;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletResponse;

public class PostFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "post";
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
		HttpServletResponse response = ctx.getResponse();

		response.addHeader("X-Foo", UUID.randomUUID().toString());

		ctx.getZuulResponseHeaders().removeIf(ssp -> ssp.first().toLowerCase().startsWith("access-control-allow")); //remover o que está vindo do microserviço, porque será reescrito no bean do cors

		System.out.println(
				"\n" +
				"In zuul after PosFilter" + "\n" +
				"Response Status : " + response.getStatus() + "\n" +
				"Response URL : " + ctx.getRequest().getRequestURL().toString() + "\n" +
				"Response Headers Key Set: " + ctx.getZuulResponseHeaders().stream().map(ssp -> ssp.first()).collect(Collectors.toList()) + "\n" +
				"Response Headers Values Set: " + ctx.getZuulResponseHeaders().stream().map(ssp -> ssp.second()).collect(Collectors.toList()) + "\n" +
				"Response Body: " + ctx.getResponseBody() +
				"\n"
		);

		return null;
	}

}
