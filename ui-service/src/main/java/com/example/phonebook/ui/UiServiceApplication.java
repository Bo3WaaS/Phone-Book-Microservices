package com.example.phonebook.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableOAuth2Sso
@EnableDiscoveryClient
@EnableFeignClients
public class UiServiceApplication extends WebSecurityConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(UiServiceApplication.class, args);
	}
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
          .authorizeRequests()
          .antMatchers("/login**")
          .permitAll()
          .anyRequest()
          .authenticated();
    }
	
}
