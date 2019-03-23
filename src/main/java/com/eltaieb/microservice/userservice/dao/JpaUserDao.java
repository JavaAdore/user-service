package com.eltaieb.microservice.userservice.dao;
import org.springframework.data.repository.CrudRepository;

import com.eltaieb.microservice.userservice.entity.BaseUserEntity;

public interface JpaUserDao extends CrudRepository<BaseUserEntity,Long>{
 
	 
}
