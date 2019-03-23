package com.eltaieb.microservice.userservice.facade;

import java.util.List;

import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.userservice.service.model.ChangePasswordModel;
import com.eltaieb.microservice.userservice.service.model.UserAddressModel;
import com.eltaieb.microservice.userservice.service.model.UserModel;


public interface UserServiceFacade {

 
	Long registerNewPublicUser(UserModel userModel) throws ServiceException;
	
    Long updateUser(Long userId  , UserModel userModel) throws ServiceException ;

	Long addUserAddress(Long userId, UserAddressModel userAddress) throws ServiceException;

	Long updateUserAddress(Long userId, Long userAddressId, UserAddressModel userAddress) throws ServiceException;

	Long deleteUserAddress(Long userId, Long userAddressId) throws ServiceException;

	List<UserAddressModel> getUserAddresses(Long userId) throws ServiceException;

	Long setUserDefaultAddress(Long userId, Long userAddressId) throws ServiceException;

	UserModel getUserModel(Long userId) throws ServiceException;

	void changePassword(Long loginUserId, ChangePasswordModel changePasswordModel);

	Long addNewSystemUser(String userName)throws ServiceException;

	Long assignUserToEntity(Long userId, Long entityId)throws ServiceException;
	 
}
