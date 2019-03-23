package com.eltaieb.microservice.userservice.dao;

import org.springframework.data.repository.CrudRepository;

import com.eltaieb.microservice.userservice.entity.UserEntityEntity;

public interface JpaUserEntityDao extends CrudRepository<UserEntityEntity,Long> {

	 
	
 
}
