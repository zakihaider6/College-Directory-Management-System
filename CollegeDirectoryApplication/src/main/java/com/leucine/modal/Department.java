package com.leucine.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Department {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="department_seq")
	@SequenceGenerator(name="department_seq", sequenceName="department_seq",allocationSize=1, initialValue=1)
    private Integer id;

	@NotBlank(message ="department name should not Blank." )
	@NotEmpty(message ="department neme should not Empty." )
	@NotNull(message ="department name should not Null." )
    @Size(max = 100)
    @Column(nullable = false)
    private String name;

    private String description;

 
}
