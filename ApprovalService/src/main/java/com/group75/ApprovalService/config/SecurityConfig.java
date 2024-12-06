package com.group75.ApprovalService.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/approvals/**").hasRole("staff")
                .anyRequest().permitAll()
                .and()
                .oauth2ResourceServer()
                .jwt();
    }
}
