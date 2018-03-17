package com.example.phonebook.ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties
@RefreshScope(proxyMode=ScopedProxyMode.DEFAULT)
@Component
public class ClientConfig {

	@Value("${security.oauth2.client.client-id}")
	private String id;
	@Value("${security.oauth2.client.client-secret}")
	private String secret;
	@Value("${security.oauth2.client.access-token-uri}")
	private String accessTokenUri;
	@Value("${client.user-id-uri}")
	private String userIdUri;

	public ClientConfig() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAccessTokenUri() {
		return accessTokenUri;
	}

	public void setAccessTokenUri(String accessTokenUri) {
		this.accessTokenUri = accessTokenUri;
	}

	public String getUserIdUri() {
		return userIdUri;
	}

	public void setUserIdUri(String userIdUri) {
		this.userIdUri = userIdUri;
	}

}
