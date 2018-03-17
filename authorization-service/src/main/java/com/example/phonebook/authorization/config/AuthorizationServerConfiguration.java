package com.example.phonebook.authorization.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private DataSource dataSource;
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;

	public AuthorizationServerConfiguration(DataSource dataSource, AuthenticationManager authenticationManager,
			UserDetailsService userDetailsService) {
		this.dataSource = dataSource;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(new JdbcTokenStore(dataSource))
				.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("phonebookclient").secret("secret").scopes("client")
				.authorities("ROLE_CLIENT").accessTokenValiditySeconds(86400).refreshTokenValiditySeconds(0)
				.authorizedGrantTypes("password", "client_credentials").and().withClient("phonebookui")
				.secret("secret").scopes("client").authorities("ROLE_CLIENT").accessTokenValiditySeconds(86400)
				.refreshTokenValiditySeconds(0).authorizedGrantTypes("authorization_code", "client_credentials")
				.autoApprove(true);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()");
	}

}
