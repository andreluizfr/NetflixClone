package com.example.ApiGateway.Filters;

import java.util.Date;
import java.util.UUID;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AddHeadersPostFilter extends ZuulFilter {

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
		//remover o que está vindo do microserviço, porque será reescrito no bean do cors
		ctx.getZuulResponseHeaders().removeIf(ssp -> ssp.first().toLowerCase().startsWith("access-control-allow")); 
	}

}
