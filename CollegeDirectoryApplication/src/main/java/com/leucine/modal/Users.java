package com.leucine.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "User")
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="user_seq")
	@SequenceGenerator(name="user_seq", sequenceName="user_seq",allocationSize=1, initialValue=1)
	
	private Integer id;
	
    @NotBlank
    @Size(max = 50)
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "password should not blank")
    @Size(max = 255)
    @Column(nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "name should not blank")
    @NotNull(message = "name should not null")
    @Size(max = 100)
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "name should not blank")
    @Email(message = "email shouls be in right format")
    @Size(max = 100)
    @Column(unique = true, nullable = false)
    private String email;

    @Size(max = 15)
	@Column(unique = true)
	@NotNull(message ="Mobile No should not Null." )
	@Pattern(regexp = "^[6-9]\\d{9}$",message= "Mobile Number Should be of 10 digits and starts with 6-9")
    private String phone;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private FacultyProfile facultyProfile;
    
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private AdministratorProfile administratorProfile;
    
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private StudentProfile studentProfile;
	
}
