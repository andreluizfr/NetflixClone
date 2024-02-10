package com.example.ApiGateway.Filters;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ApiGateway.Authentication.Business.TokenService;
import com.example.ApiGateway.Authentication.DataProvider.UserRepository;
import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AddHeadersPreFilter extends ZuulFilter {

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
		return 1;
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

		addHeadersToRequestContext(ctx, this.recoverToken(request));

		return null;
	}

	private String recoverToken(HttpServletRequest request) {

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null)
			return null;

		return authHeader.replace("Bearer ", "");
	}

	private void addHeadersToRequestContext(RequestContext ctx, String token) {

		Set<String> oldHeadersKeySet = ctx.getZuulRequestHeaders().keySet();

		try {
			tokenService.validateToken(token);
			ctx.addZuulRequestHeader("Authorization", "Bearer " + token);
		} catch (JWTVerificationException e) {
			System.out.println("failed in validate token");
		}

		ctx.addZuulRequestHeader("Content-Type", "application/json");
		ctx.addZuulRequestHeader("Content-Length", String.valueOf(ctx.getRequest().getContentLength()));
		if(oldHeadersKeySet.contains("Accept-Encoding")){
			ctx.addZuulRequestHeader("Accept-Encoding", "gzip");
		}	
		ctx.addZuulRequestHeader("X-Start-Date", String.valueOf(new Date()));
	}
}
