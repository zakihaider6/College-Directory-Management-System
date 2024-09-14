package com.leucine.modal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="course_seq")
	@SequenceGenerator(name="course_seq", sequenceName="course_seq",allocationSize=1, initialValue=1)
    private Integer id;
	
	@Column(length = 100,nullable = false,unique = true)
    private String title;
	
	private String description;
	
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "faculty_id", nullable = false)
    private FacultyProfile faculty;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

}
