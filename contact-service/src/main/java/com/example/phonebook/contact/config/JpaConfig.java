package com.example.phonebook.contact.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
	
	@Qualifier("customAuditorAware")
	private AuditorAware<Long> customAuditAware;

	public JpaConfig(AuditorAware<Long> customAuditAware) {
		this.customAuditAware = customAuditAware;
	}

	@Bean
	@Primary
	public AuditorAware<Long> auditorAware() {
		return customAuditAware;
	}
	
}
