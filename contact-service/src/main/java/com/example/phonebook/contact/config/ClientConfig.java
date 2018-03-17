package com.example.phonebook.contact.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
@ConfigurationProperties("client")
@RefreshScope(proxyMode=ScopedProxyMode.DEFAULT)
public class ClientConfig {

	private String id;
	private String secret;
	private String uri;

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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
