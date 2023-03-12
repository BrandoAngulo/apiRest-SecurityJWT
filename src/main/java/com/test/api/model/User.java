package com.test.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="usuarios")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "last_name")
	    private String lastName;

	    @Column(name = "date_birth")
	    private LocalDate dateOfBirth;

	    @Column(name = "token")
	    private String token;

	    @Column(name = "pass")
	    private String pass;

	    @Column(name = "mobile_phone")
	    private String mobilePhone;

	    @Column(name = "email")
	    private String email;
}
