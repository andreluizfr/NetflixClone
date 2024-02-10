package com.example.ApiGateway.Filters;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ApiGateway.Authentication.Business.TokenService;
import com.example.ApiGateway.Authentication.DataProvider.UserRepository;
import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class LogRequestPreFilter extends ZuulFilter {

	@Autowired
	TokenService tokenService;

	@Autowired
	UserRepository userRepository;

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

	@Autowired
	Gson gson;

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
		
		System.out.println(
				"\n" +
				"In zuul after PreFilter" + "\n" +
				"Request Method : " + request.getMethod() + "\n" +
				"Request URL : " + request.getRequestURL().toString() + "\n" +
				"Request Query: " + request.getQueryString() + "\n" +
				"Request Headers Key Set: " + ctx.getZuulRequestHeaders().keySet().toString() + "\n" +
				"Request Headers Values Set: " + ctx.getZuulRequestHeaders().values().toString() + "\n" +
				"Request Body: " + requestBody +
				"\n"
		);

		return null;
	}
}
