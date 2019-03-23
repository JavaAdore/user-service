package com.eltaieb.microservice.userservice.service.api;

import java.util.Optional;

import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.userservice.entity.BaseUserEntity;
import com.eltaieb.microservice.userservice.entity.UserEntityEntity;
import com.eltaieb.microservice.userservice.service.model.UserModel;
 

public interface UserEntityService {

	UserEntityEntity assignUserToEntity(Long userId, Long entityId);

 
	 


	 
	
	
}
