package com.leucine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.leucine.modal.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileDTO {
   
	@NotBlank(message ="userName should not Blank." )
	@NotEmpty(message ="userName should not Empty." )
	@NotNull(message ="userName should not Null." )
    @Size(max = 50)
    @Column(unique = true, nullable = false)
    private String username;

	
    @Size(max = 255)
    @Column(nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Email(message = "Email should be in right format")
    @Size(max = 100)
    @Column(unique = true, nullable = false)
    private String email;

    @Size(max = 15)
	@Column(unique = true)
	@Pattern(regexp = "^[6-9]\\d{9}$",message= "Mobile Number Should be of 10 digits and starts with 6-9")
    private String phone;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Column(name = "photo", length = 255)
    private String photo;

    @Column(length = 50)
    private String year;

}
