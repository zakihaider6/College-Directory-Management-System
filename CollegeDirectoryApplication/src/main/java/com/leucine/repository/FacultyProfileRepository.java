package com.leucine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leucine.modal.FacultyProfile;

public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Integer>{
	//Optional<FacultyProfile> findByUsername(String username);
}
