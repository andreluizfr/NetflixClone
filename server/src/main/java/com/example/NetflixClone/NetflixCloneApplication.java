package com.example.NetflixClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//import javax.faces.webapp.FacesServlet;
import java.util.EventListener;
//import javax.servlet.Servlet;

import com.sun.faces.config.ConfigureListener;

import jakarta.servlet.Servlet;



@SpringBootApplication
public class NetflixCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixCloneApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean<Servlet> facesServletRegistration() {
		// ServletRegistrationBean<Servlet> registration = new
		// ServletRegistrationBean<Servlet>(new FacesServlet(), "*.xhtml");
		ServletRegistrationBean<Servlet> registration = new ServletRegistrationBean<Servlet>();
		registration.setLoadOnStartup(1);
		registration.addUrlMappings("*.jr");
		return registration;
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
			servletContext.setInitParameter("primefaces.THEME", "sunny");
		};
	}

	@Bean
	public ServletListenerRegistrationBean<EventListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<EventListener>(new ConfigureListener());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
