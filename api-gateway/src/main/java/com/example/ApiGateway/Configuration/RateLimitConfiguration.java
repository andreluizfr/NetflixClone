package com.example.ApiGateway.Configuration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.DefaultRateLimiterErrorHandler;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.RateLimiterErrorHandler;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.DefaultRateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitExceededException;
import com.netflix.zuul.context.RequestContext;

@Configuration
public class RateLimitConfiguration {
    
    @Bean
    RateLimitKeyGenerator ratelimitKeyGenerator(RateLimitProperties properties, RateLimitUtils rateLimitUtils) {
        return new DefaultRateLimitKeyGenerator(properties, rateLimitUtils) {
            @Override
            public String key(HttpServletRequest request, Route route, RateLimitProperties.Policy policy) {
                return super.key(request, route, policy) + ":" + request.getMethod();
            }
        };
    }

    /*
    @Bean
    RateLimiterErrorHandler rateLimitErrorHandler() {
        return new DefaultRateLimiterErrorHandler() {
            @Override
            public void handleSaveError(String key, Exception e) {
                // custom code
            }

            @Override
            public void handleFetchError(String key, Exception e) {
                // custom code
            }

            @Override
            public void handleError(String msg, Exception e) {
                // custom code
            }
        };
    }
     */

    @EventListener
    public void observe(RateLimitExceededException e) {

        RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
        
        request.getRemoteAddr();

        System.out.println(request.getRemoteAddr() + " = " + e.getMessage());
    }
}
