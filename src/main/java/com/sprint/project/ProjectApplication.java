package com.sprint.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.sprint.project.authorization.security.JwtFilter;

@SpringBootApplication
@ComponentScan(basePackages = "com.sprint.project")
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    // ✅ REGISTER JWT FILTER
    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> filterRegistrationBean() {
        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(jwtFilter());
        registration.addUrlPatterns("/api/*");
        return registration;
    }
}