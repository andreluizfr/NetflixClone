package com.example.ApiGateway;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.ApiGateway.Filters.ErrorFilter;
import com.example.ApiGateway.Filters.PostFilter;
import com.example.ApiGateway.Filters.PreFilter;
import com.example.ApiGateway.Filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}

	@Bean
	RouteFilter routeFilter() {
		return new RouteFilter();
	}

	@Bean
	ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	PostFilter postFilter() {
		return new PostFilter();
	}

}
