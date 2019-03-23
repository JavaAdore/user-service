package com.eltaieb.microservice.userservice.service.impl;

import org.springframework.stereotype.Service;

import com.eltaieb.microservice.userservice.dao.JpaUserDao;
import com.eltaieb.microservice.userservice.dao.JpaUserEntityDao;
import com.eltaieb.microservice.userservice.entity.BaseUserEntity;
import com.eltaieb.microservice.userservice.entity.UserEntityEntity;
import com.eltaieb.microservice.userservice.service.api.UserEntityService;

import lombok.extern.java.Log;

@Log
@Service
public class UserEntityServiceBean implements UserEntityService { 
	
	private JpaUserEntityDao jpaUserEntityDao;
	
	private JpaUserDao jpaUserDao;
	public UserEntityServiceBean(JpaUserEntityDao jpaUserEntityDao , JpaUserDao jpaUserDao)
	{
		this.jpaUserEntityDao=jpaUserEntityDao;
		this.jpaUserDao=jpaUserDao;
	}
	
	
	
	public UserEntityEntity assignUserToEntity(Long userId, Long entityId) {
		BaseUserEntity baseUserEntity =	jpaUserDao.findById(userId).get();

		UserEntityEntity userEntity = new UserEntityEntity();
		userEntity.setEntityId(entityId);
		userEntity.setUser(baseUserEntity);
		return jpaUserEntityDao.save(userEntity);
	}

 
	 
}
