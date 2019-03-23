package com.eltaieb.microservice.userservice.service.api;

import java.util.Optional;

import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.userservice.entity.BaseUserEntity;
import com.eltaieb.microservice.userservice.service.model.UserModel;
 

public interface UserService {

 
	Long add(UserModel userModel);
	Long update(Long id , UserModel userModel) throws ServiceException;
	Optional<BaseUserEntity> getBaseUserEntity(Long userId);


	 
	
	
}
