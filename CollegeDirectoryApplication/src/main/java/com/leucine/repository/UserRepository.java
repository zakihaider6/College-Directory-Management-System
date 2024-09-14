package com.leucine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leucine.modal.StudentProfile;
import com.leucine.modal.Users;

public interface UserRepository extends JpaRepository<Users,Integer>{
	
//	List<Users> findByName(String name);
	Optional<Users> findByEmail(String email);
	Optional<Users> findByUsername(String username);
	List<Users> findByNameContaining(String name);
	
}
