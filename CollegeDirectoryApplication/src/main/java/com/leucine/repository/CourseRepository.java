package com.leucine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leucine.modal.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
