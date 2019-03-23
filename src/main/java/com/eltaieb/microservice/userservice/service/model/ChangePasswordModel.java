package com.eltaieb.microservice.userservice.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChangePasswordModel implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String oldPassword;
	
	private String newPassword;
}
