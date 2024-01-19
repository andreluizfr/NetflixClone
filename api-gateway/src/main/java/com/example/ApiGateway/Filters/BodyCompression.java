package com.example.ApiGateway.Filters;

import java.util.UUID;
import java.util.stream.Collectors;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletResponse;

public class BodyCompression extends ZuulFilter {

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

        //adicionar compressão e 

		return null;
	}

}
