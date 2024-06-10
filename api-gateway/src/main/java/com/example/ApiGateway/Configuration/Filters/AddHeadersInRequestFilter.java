package com.example.ApiGateway.Configuration.Filters;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ApiGateway.Authentication.Business.TokenService;
import com.example.ApiGateway.Authentication.DataProvider.UserRepository;
import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AddHeadersInRequestFilter extends ZuulFilter {

	private static final Logger logger = LogManager.getLogger(AddHeadersInRequestFilter.class);

	@Autowired
	TokenService tokenService;

	@Autowired
	UserRepository userRepository;

	@Override
	public String filterType() {
		return "route";
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

		Map<String, String> oldHeadersKeySet = ctx.getZuulRequestHeaders()
				.entrySet()
				.stream()
				.map(entry -> Map.entry(entry.getKey().toLowerCase(), entry.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		try {
			tokenService.validateToken(token);
			ctx.addZuulRequestHeader("Authorization", "Bearer " + token);
		} catch (JWTVerificationException e) {
			logger.info("Failed in validating token.");
		}

		ctx.addZuulRequestHeader("Content-Type", oldHeadersKeySet.get("content-type"));
		ctx.addZuulRequestHeader("Content-Length", String.valueOf(ctx.getRequest().getContentLength()));
		ctx.addZuulRequestHeader("Accept-Encoding", "gzip");
		ctx.addZuulRequestHeader("X-Start-Date", String.valueOf(new Date()));
		ctx.addZuulRequestHeader("Credentials", oldHeadersKeySet.get("credentials"));
	}
}
