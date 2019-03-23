package com.eltaieb.microservice.userservice.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.base.feignclient.UserLoginService;
import com.eltaieb.microservice.userservice.entity.BaseUserEntity;
import com.eltaieb.microservice.userservice.entity.UserAddressEntity;
import com.eltaieb.microservice.userservice.entity.UserEntityEntity;
import com.eltaieb.microservice.userservice.service.api.UserAddressService;
import com.eltaieb.microservice.userservice.service.api.UserEntityService;
import com.eltaieb.microservice.userservice.service.api.UserService;
import com.eltaieb.microservice.userservice.service.api.ValidationService;
import com.eltaieb.microservice.userservice.service.model.ChangePasswordModel;
import com.eltaieb.microservice.userservice.service.model.UserAddressModel;
import com.eltaieb.microservice.userservice.service.model.UserModel;
  
@Service 
public class UserServiceFacadeBean implements UserServiceFacade {

  	private ValidationService validationService;
	private UserService userService;
	private UserAddressService userAddressService;
	private UserLoginService userLoginService;
	private UserEntityService userEntityService;
	public UserServiceFacadeBean(UserService userSerice,ValidationService validationService, 
			UserAddressService userAddressService,UserLoginService userLoginService,
			UserEntityService userEntityService) {
			 
		super();
		this.userService = userSerice;
  		this.validationService = validationService;
  		this.userAddressService=userAddressService;
  		this.userLoginService=userLoginService;
  		this.userEntityService=userEntityService;

 	}

	@Override
	public Long registerNewPublicUser(UserModel userModel) throws ServiceException {
		validationService.validateRegisterNewPublicUser(userModel);
		return userService.add(userModel);
	}

	 

	@Override
	public Long updateUser(Long userId  , UserModel userModel) throws ServiceException {
		
		validationService.validateRegisterNewPublicUser(userModel);
		validationService.validateUpdateUserProfilePictureUrl( userId,  userModel.getProfilePictureUrl()); 
		userId = userService.update(userId,userModel);
 		return userId;
	}
	@Transactional 
	@Override
	public Long addUserAddress(Long userId, UserAddressModel userAddress) throws ServiceException {
		 validationService.validateAddUserAddress(userId,userAddress);
		 Long addressId =  userAddressService.add(userId , userAddress);
		 return addressId;
		 
	}

	@Override
	public Long updateUserAddress(Long userId, Long userAddressId, UserAddressModel userAddress) throws ServiceException {
		
			validationService.validateUpdateUserAddress(userId,userAddressId,userAddress);
			 Long addressId =  userAddressService.update(userId ,userAddressId, userAddress);
		 return addressId;
	}

	@Override
	public Long deleteUserAddress(Long userId, Long userAddressId) throws ServiceException {
		validationService.validateDeleteUserAddress(userId,userAddressId);
		Long newUserAddressId = userAddressService.deleteUserAddress(userId,userAddressId);
		return newUserAddressId;
	}

	@Override
	public List<UserAddressModel> getUserAddresses(Long userId) throws ServiceException {
		validationService.validateGetUserAddresses(userId);
		List<UserAddressEntity> userAddreses = userAddressService.getUserAddresses(userId);
		List<UserAddressModel> userModel = toUserAddressModelList(userAddreses);
		return userModel;
		
	}

	private List<UserAddressModel> toUserAddressModelList(List<UserAddressEntity> userAddreses) {
 		return  userAddreses.stream().map(uae -> toUserAddressModel(uae)).collect(Collectors.toList());
 	}

	private UserAddressModel toUserAddressModel(UserAddressEntity userAddressEntity) {
		UserAddressModel userAddressModel = new UserAddressModel();
		userAddressModel.setId(userAddressEntity.getId());
		userAddressModel.setBuildingNumber(userAddressEntity.getBuildingNumber());
		userAddressModel.setDistict(userAddressEntity.getDistict());
		userAddressModel.setStreetName(userAddressEntity.getStreetName());
		userAddressModel.setFloorNumber(userAddressEntity.getFloorNumber());
		userAddressModel.setLatitude(userAddressEntity.getLatitude());
		userAddressModel.setLongitude(userAddressEntity.getLongitude());
		userAddressModel.setLocationId(userAddressEntity.getLocationId());
		userAddressModel.setDefaultAddress(userAddressEntity.getDefaultAddress());
		return userAddressModel;
	}

	@Transactional
	@Override
	public Long setUserDefaultAddress(Long userId, Long userAddressId) throws ServiceException {
		validationService.validateSetUserDefaultAddress( userId,  userAddressId);
		userAddressService.setUserDefaultAddress( userId,  userAddressId);
		return userAddressId;
	}

	@Override
	public UserModel getUserModel(Long userId) throws ServiceException {
		validationService.validateGetUserModel( userId);
	Optional<BaseUserEntity> baseUserEntity = 	userService.getBaseUserEntity(userId);
		 return toUserModel(baseUserEntity.get());
	}

	
	private UserModel toUserModel(BaseUserEntity baseUserEntity) {
		UserModel userModel = new UserModel();
		userModel.setFirstName(baseUserEntity.getFirstName());
		userModel.setLastName(baseUserEntity.getLastName());
		userModel.setBirthDate(baseUserEntity.getBirthDate());
		userModel.setGender(baseUserEntity.getGender());
		userModel.setProfilePictureUrl(baseUserEntity.getProfilePictureUrl());
		return userModel;
	}

	@Override
	public void changePassword(Long userLoginId, ChangePasswordModel changePasswordModel) {
		userLoginService.changePassword(userLoginId, changePasswordModel);		
	}

	@Override
	public Long addNewSystemUser(String userName) {
		UserModel userModel = new UserModel();
		userModel.setFirstName(userName);
		return userService.add(userModel);

	}

	@Override
	public Long assignUserToEntity(Long userId, Long entityId) throws ServiceException {
		validationService.validateAssignUserToEntity(userId,entityId);
		 UserEntityEntity userEntity = userEntityService.assignUserToEntity(userId,entityId);
		 return userEntity.getId();
	}

	 

}
