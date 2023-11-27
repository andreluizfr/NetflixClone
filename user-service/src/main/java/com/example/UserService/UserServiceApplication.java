package com.example.UserService;

import org.apache.kafka.clients.admin.NewTopic;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class UserServiceApplication{

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Value("${spring.datasource.username}")
	private String dataSourceUsername;

	@Value("${spring.datasource.password}")
	private String dataSourcePassword;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
    NewTopic userCreatedTopic() {
        return new NewTopic("user-created", 1, (short) 1);
    }
	/* 
	@Bean
	public void flyway() {
		
		Flyway flyway = Flyway.configure().dataSource(dataSourceUrl, dataSourceUsername, dataSourcePassword).load();

		flyway.migrate();
	}
	*/
}
