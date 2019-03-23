package com.eltaieb.microservice.userservice.service.api;

import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.userservice.service.model.UserAddressModel;
import com.eltaieb.microservice.userservice.service.model.UserModel;

public interface ValidationService {

	void validateRegisterNewPublicUser(UserModel userModel) throws ServiceException;

	void validateUpdateUserProfilePictureUrl(Long userId, String url) throws ServiceException;

	void validateAddUserAddress(Long userId, UserAddressModel userAddress)throws ServiceException;

	void validateUpdateUserAddress(Long userId, Long userAddressId, UserAddressModel userAddress) throws ServiceException;

	void validateDeleteUserAddress(Long userId, Long userAddressId)throws ServiceException;

	void validateGetUserAddresses(Long userId)throws ServiceException;

	void validateSetUserDefaultAddress(Long userId, Long userAddressId) throws ServiceException;

	void validateGetUserModel(Long userId) throws ServiceException;

	void validateAssignUserToEntity(Long userId, Long entityId) throws ServiceException;

	 
}
