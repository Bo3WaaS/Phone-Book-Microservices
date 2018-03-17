package com.example.phonebook.ui.service;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.stereotype.Service;

import com.example.phonebook.ui.config.ClientConfig;

@Service
public class DefaultUserService implements UserService {

	private ClientConfig clientConfig;

	public DefaultUserService(ClientConfig clientConfig) {
		this.clientConfig = clientConfig;
	}

	@Override
	public Long getUserId(String username) {
		OAuth2RestTemplate rest = new OAuth2RestTemplate(getCcs());
		Long id = rest.getForObject(clientConfig.getUserIdUri() + "/" + username, Long.class);
		return id;
	}

	private ClientCredentialsResourceDetails getCcs() {
		ClientCredentialsResourceDetails ccs = new ClientCredentialsResourceDetails();
		ccs.setAccessTokenUri(clientConfig.getAccessTokenUri());
		ccs.setClientId(clientConfig.getId());
		ccs.setClientSecret(clientConfig.getSecret());
		ccs.setGrantType("client_credentials");
		ccs.setClientAuthenticationScheme(AuthenticationScheme.header);
		return ccs;
	}

}
