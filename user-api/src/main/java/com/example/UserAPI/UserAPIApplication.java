package com.example.UserAPI;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;


@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableHystrix
@EnableHystrixDashboard //endpoint /hystrix
public class UserAPIApplication{

	public static void main(String[] args) {
		SpringApplication.run(UserAPIApplication.class, args);
	}

	@Bean
    NewTopic userCreatedTopic() {
        return new NewTopic("user-created", 1, (short) 1);
    }

	@Bean
	Customizer<HystrixCircuitBreakerFactory> defaultConfig() {
		return factory -> factory.configureDefault(id -> HystrixCommand.Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey(id))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
				.withExecutionTimeoutInMilliseconds(4000)));
	}

	/* 
	@Bean
	Customizer<HystrixCircuitBreakerFactory> customizer() {
		return factory -> factory.configure(builder -> builder
				.commandProperties(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000)), "foo", "bar");
	}
	*/
}
