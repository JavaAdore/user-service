package com.eltaieb.microservice.userservice.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.eltaieb.microservice.base.config.BaseServiceConstant;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BaseUserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String password;

	private String firstName;

	private String lastName;

	private String gender;

	private String locale;
    
	@JsonFormat(pattern = BaseServiceConstant.DEFAULT_DATE_FORMAT)
	private LocalDate birthDate;
}
