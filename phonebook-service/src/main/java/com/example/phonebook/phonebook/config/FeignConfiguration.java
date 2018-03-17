package com.example.phonebook.phonebook.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableConfigurationProperties(ClientConfig.class)
@EnableOAuth2Client
public class FeignConfiguration {
	
	private ClientConfig clientConfig;

	public FeignConfiguration(ClientConfig clientConfig) {
		this.clientConfig = clientConfig;
	}
	
	@Bean
    @Qualifier("ClientCredentialsOAuth2FeignRequestInterceptor")
    public OAuth2FeignRequestInterceptor oauth2schemeRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), oauth2schemeResourceDetails());
    }
	
	@Bean
    public ClientCredentialsResourceDetails oauth2schemeResourceDetails() {
		ClientCredentialsResourceDetails ccs = new ClientCredentialsResourceDetails();
		ccs.setAccessTokenUri(clientConfig.getUri());
		ccs.setClientId(clientConfig.getId());
		ccs.setClientSecret(clientConfig.getSecret());
		ccs.setGrantType("client_credentials");
		ccs.setClientAuthenticationScheme(AuthenticationScheme.header);
        return ccs;
    }

}
