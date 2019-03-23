package com.eltaieb.microservice.userservice.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.eltaieb.microservice.base.model.Gender;

import lombok.Data;

@Data
@Entity(name="BaseUser")
@Table(name="BASE_USER")
public class BaseUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="base_user_pk_seq")
	@SequenceGenerator(name="base_user_pk_seq" , sequenceName="base_user_pk_seq",allocationSize=1)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name="birthdate")
 	private LocalDate birthDate;

	private String profilePictureUrl;
	 
 
}
