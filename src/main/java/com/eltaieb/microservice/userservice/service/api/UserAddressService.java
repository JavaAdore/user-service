package com.eltaieb.microservice.userservice.service.api;

import java.util.List;

import com.eltaieb.microservice.userservice.entity.UserAddressEntity;
import com.eltaieb.microservice.userservice.service.model.UserAddressModel;

public interface UserAddressService {

	Long add(Long userId, UserAddressModel userAddress);

	Long update(Long userId, Long userAddressId, UserAddressModel userAddress);

	Long deleteUserAddress(Long userId, Long userAddressId);

	List<UserAddressEntity> getUserAddresses(Long userId);

	void setUserDefaultAddress(Long userId, Long userAddressId);
	 
	 
	
	
}
