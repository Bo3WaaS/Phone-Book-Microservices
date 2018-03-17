package com.example.phonebook.authorization.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.phonebook.authorization.entity.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long>{

}
