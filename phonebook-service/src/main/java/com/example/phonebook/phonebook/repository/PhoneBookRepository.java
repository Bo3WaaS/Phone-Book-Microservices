package com.example.phonebook.phonebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.phonebook.phonebook.entity.PhoneBook;

@Repository
public interface PhoneBookRepository extends CrudRepository<PhoneBook, Long>{
	
	List<PhoneBook> findPhoneBookByUserId(Long userId);
	@Query("SELECT p.id FROM PhoneBook p where p.userId = :userId") 
	List<Long> findPhoneBookIdByUserId(@Param("userId") Long userId);

}
