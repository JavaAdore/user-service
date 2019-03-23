package com.eltaieb.microservice.userservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eltaieb.microservice.userservice.dao.JpaUserAddressDao;
import com.eltaieb.microservice.userservice.dao.JpaUserDao;
import com.eltaieb.microservice.userservice.entity.BaseUserEntity;
import com.eltaieb.microservice.userservice.entity.UserAddressEntity;
import com.eltaieb.microservice.userservice.service.api.UserAddressService;
import com.eltaieb.microservice.userservice.service.model.UserAddressModel;

import lombok.extern.java.Log;

@Log
@Service
public class UserAddressServiceBean implements UserAddressService {

 
	private JpaUserAddressDao jpaUserAddressDao;
	private JpaUserDao jpaUserDao;
	public UserAddressServiceBean(JpaUserAddressDao jpaUserAddressDao, JpaUserDao jpaUserDao) {
		this.jpaUserAddressDao = jpaUserAddressDao;
		this.jpaUserDao=jpaUserDao;
	}

	@Override
	public Long add(Long userId, UserAddressModel userAddress) {
		
		adjustUserDefaultAddress(userId , userAddress);
		UserAddressEntity userAddressEntity = 	toUserAddressEntity(userAddress);
		Optional<BaseUserEntity> userEntityOptional = jpaUserDao.findById(userId);
		// no sense  to come with null .. validation service will check first
		userAddressEntity.setUser(userEntityOptional.get());
		userAddressEntity = jpaUserAddressDao.save(userAddressEntity);
		return userAddressEntity.getId();
	}

	private UserAddressEntity toUserAddressEntity(UserAddressModel userAddress) {
		UserAddressEntity userAddressEntity = new UserAddressEntity();
		userAddressEntity.setBuildingNumber(userAddress.getBuildingNumber());
		userAddressEntity.setDistict(userAddress.getDistict());
		userAddressEntity.setStreetName(userAddress.getStreetName());
		userAddressEntity.setFloorNumber(userAddress.getFloorNumber());
		userAddressEntity.setLatitude(userAddress.getLatitude());
		userAddressEntity.setLongitude(userAddress.getLongitude());
		userAddressEntity.setLocationId(userAddress.getLocationId());
		userAddressEntity.setDeleted(Boolean.FALSE);
		userAddressEntity.setDefaultAddress(userAddress.getDefaultAddress());
		return userAddressEntity;
	}

	private void adjustUserDefaultAddress(Long userId, UserAddressModel userAddress) {
		if (userHasNoAddress(userId)) {
			userAddress.setDefaultAddress(Boolean.TRUE);
		} else {
			if (Boolean.TRUE == userAddress.getDefaultAddress()) {
				jpaUserAddressDao.removeDefaultAddressFlagForUser(userId);
			}
		}		
	}

	private boolean userHasNoAddress(Long userId) {

		List<UserAddressEntity> userAddresses = jpaUserAddressDao.getUserAddresses(userId);
		return userAddresses.isEmpty();
	}

	@Override
	public Long update(Long userId,Long userAddressId, UserAddressModel userAddress) {
		//TODO use mapper later on 
		Optional<UserAddressEntity> userAddressEntityOptional = jpaUserAddressDao.findById(userAddressId);
		UserAddressEntity userAddressEntity = userAddressEntityOptional.get();
		UserAddressEntity updatedEntity = 	toUserAddressEntity(userAddress);
		updatedEntity.setId(userAddressId);
		updatedEntity.setUser(userAddressEntity.getUser());
		jpaUserAddressDao.save(updatedEntity);
		return userAddressId;
	}

	@Override
	public Long deleteUserAddress(Long userId, Long userAddressId) {
		Optional<UserAddressEntity> userAddressEntityOptional = jpaUserAddressDao.findById(userAddressId);
		UserAddressEntity userAddressEntity = userAddressEntityOptional.get();
		boolean isDefault = userAddressEntity.getDefaultAddress();
		Long userDefaultAddressId = null;
		if(Boolean.FALSE ==isDefault)
		{
			userDefaultAddressId =  getUserDefaultAddressId(userId);
		}else
		{
			userDefaultAddressId = assignRandomAddressForUserFromHis(userId);
		}
		userAddressEntity.setDeleted(Boolean.TRUE);
		jpaUserAddressDao.save(userAddressEntity);
		
		return userDefaultAddressId;
		
		
	}

	private Long assignRandomAddressForUserFromHis(Long userId) {
		List<UserAddressEntity> userAddresses = jpaUserAddressDao.getUserAddresses(userId);
		if(Boolean.FALSE == userAddresses.isEmpty())
		{
			UserAddressEntity userAddressEntity =  userAddresses.get(0);
			 userAddressEntity.setDefaultAddress(Boolean.TRUE);
			 jpaUserAddressDao.save(userAddressEntity);
			 Long userDefaultAddressId = userAddressEntity.getId();
			 return userDefaultAddressId;
		}
		return null;
	}

	private Long getUserDefaultAddressId(Long userId) {
		Optional<UserAddressEntity> userDefaultAddressOptional = jpaUserAddressDao.findUserDefaultAddress(userId);
		if(userDefaultAddressOptional.isPresent())
		{
			return userDefaultAddressOptional.get().getId();
		}
		
		return null;
	}

	@Override
	public List<UserAddressEntity> getUserAddresses(Long userId) {
		return jpaUserAddressDao.getUserAddresses(userId);
	}

	@Override
	public void setUserDefaultAddress(Long userId, Long userAddressId) {
		jpaUserAddressDao.removeDefaultAddressFlagForUser(userId);
		Optional<UserAddressEntity> userAddressEntityOptional= jpaUserAddressDao.findById(userId);
		UserAddressEntity userAddressEntity =	userAddressEntityOptional.get();
		userAddressEntity.setDefaultAddress(Boolean.TRUE);
		jpaUserAddressDao.save(userAddressEntity);
	}

}
