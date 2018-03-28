package com.example.phonebook.authorization.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.phonebook.authorization.entity.User;
import com.example.phonebook.authorization.entity.UserRole;
import com.example.phonebook.authorization.repository.UserRepository;
import com.example.phonebook.authorization.repository.UserRoleRepository;

@Component
@Profile("dev")
public class Bootstrap implements CommandLineRunner{

	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;

	public Bootstrap(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public void run(String... arg0) throws Exception {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		user.setEnabled(true);
		
		List<UserRole> userRoles = new ArrayList<>();
		UserRole role = new UserRole();
		role.setUser(user);
		role.setRole("ROLE_ADMIN");
		userRoles.add(role);
		user.setUserRoles(userRoles);
		
		
		
		User user2 = new User();
		user2.setUsername("user");
		user2.setPassword("user");
		user2.setEnabled(true);
		
		List<UserRole> userRoles2 = new ArrayList<>();
		UserRole role2 = new UserRole();
		role2.setUser(user2);
		role2.setRole("ROLE_USER");
		userRoles2.add(role2);
		user2.setUserRoles(userRoles2);
		
		user2.setUserRoles(userRoles2);
		
		userRepository.save(user);
		userRepository.save(user2);
		userRoleRepository.save(userRoles);
		userRoleRepository.save(userRoles2);
		
		System.out.println("Users in database: " + userRepository.count());
	}

}
