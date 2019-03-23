package com.eltaieb.microservice.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
public class WebSecurityConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
        .anonymous().and()
        .authorizeRequests()
        .antMatchers("/system-user-administration/**","/administration/**")
        .permitAll().anyRequest().authenticated().and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.NEVER);
	}

	@Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         resources.resourceId("resourceIdTest");
    }
 
	 
}
