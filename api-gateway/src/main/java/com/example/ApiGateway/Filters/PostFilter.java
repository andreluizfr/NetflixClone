package com.example.ApiGateway.Filters;

import java.util.UUID;

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
		System.out.println("Using Post Filter");

        RequestContext context = RequestContext.getCurrentContext();

        HttpServletResponse servletResponse = context.getResponse();
        servletResponse.addHeader("X-Foo", UUID.randomUUID().toString());

		return null;
	}

}
