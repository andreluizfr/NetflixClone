package com.example.ApiGateway.Filters;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ApiGateway.Authentication.Business.TokenService;
import com.example.ApiGateway.Authentication.DataProvider.UserRepository;
import com.example.ApiGateway.Authentication.Models.User;
import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {

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

		addEmailHeaderToRequestContext(ctx, this.recoverToken(request));

		String requestBody;
		try {
			requestBody = new String(request.getInputStream().readAllBytes());
		} catch (IOException | RuntimeException e) {
			requestBody = "";
		}

		ctx.addZuulRequestHeader("Content-Type", "application/json");
		ctx.addZuulRequestHeader("Content-Length", String.valueOf(request.getContentLength()));

		System.out.println(
				"\n" +
				"In zuul after PreFilter" + "\n" +
				"Request Method : " + request.getMethod() + "\n" +
				"Request URL : " + request.getRequestURL().toString() + "\n" +
				"Request Query: " + request.getQueryString() + "\n" +
				"Request Headers Key Set: " + ctx.getZuulRequestHeaders().keySet().toString() + "\n" +
				"Request Headers Values: " + ctx.getZuulRequestHeaders().values().toString() + "\n" +
				"Request Body: " + requestBody +
				"\n"
		);

		return null;
	}

	private String recoverToken(HttpServletRequest request) {

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null)
			return null;

		return authHeader.replace("Bearer ", "");
	}

	private void addEmailHeaderToRequestContext(RequestContext ctx, String token) {
		try {

			String email = tokenService.validateToken(token);
			User user = (User) userRepository.findByEmail(email);

			ctx.addZuulRequestHeader("X-Logged-In-User", user.getEmail());

		} catch (JWTVerificationException e) {

			ctx.addZuulRequestHeader("X-Logged-In-User", "");
		}
	}
}
