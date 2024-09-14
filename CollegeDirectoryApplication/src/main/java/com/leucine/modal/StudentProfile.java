package com.leucine.modal;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class StudentProfile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="student_seq")
	@SequenceGenerator(name="student_seq", sequenceName="student_seq",allocationSize=1, initialValue=1)
    private Integer id;

    @Column(length = 255)
    private String photo;

    @Column(length = 50)
    private String year;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

}
