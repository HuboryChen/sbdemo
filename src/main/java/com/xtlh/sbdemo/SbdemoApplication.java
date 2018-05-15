package com.xtlh.sbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@ComponentScan(basePackages = "com.xtlh.sbdemo")
@SpringBootApplication
@EnableWebSecurity        //启用web安全
public class SbdemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SbdemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SbdemoApplication.class, args);
	}
}
