package com.leucine.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="enrollment_seq")
	@SequenceGenerator(name="enrollment_seq", sequenceName="enrollment_seq",allocationSize=1, initialValue=1)
    private Integer id ;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private StudentProfile student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

}
