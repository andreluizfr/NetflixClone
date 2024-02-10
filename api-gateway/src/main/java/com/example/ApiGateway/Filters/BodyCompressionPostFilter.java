package com.example.ApiGateway.Filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletResponse;

public class BodyCompressionPostFilter extends ZuulFilter {

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
		//return true;
		return false;
	}

	@Override
	public Object run() {

		//RequestContext ctx = RequestContext.getCurrentContext();
		//HttpServletResponse response = ctx.getResponse();

        //adicionar compressão e 

		return null;
	}

}
