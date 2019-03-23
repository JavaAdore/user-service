package com.eltaieb.microservice.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.base.model.ServiceResponse;
import com.eltaieb.microservice.base.model.SuccessServiceResponse;
import com.eltaieb.microservice.userservice.facade.UserServiceFacade;
import com.eltaieb.microservice.userservice.service.model.UserAddressModel;
import com.eltaieb.microservice.userservice.service.model.UserModel;

import lombok.extern.java.Log;

@Log
@RestController()
@RequestMapping("administration/user")
public class UserAdministrationController {

	private UserServiceFacade userServiceFacade;

	public UserAdministrationController(UserServiceFacade userServiceFacade) {
		this.userServiceFacade = userServiceFacade;
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ServiceResponse<Long> addUser(@RequestBody UserModel user) throws ServiceException {
		Long userId = userServiceFacade.registerNewPublicUser(user);
		return new SuccessServiceResponse<Long>(userId);
	}

	@RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
	public ServiceResponse<Long> updateUser(@PathVariable("userId") Long userId, @RequestBody UserModel user)
			throws ServiceException {
		userId = userServiceFacade.updateUser(userId, user);
		return new SuccessServiceResponse<Long>(userId);
	}
	
	

	@RequestMapping(path = "/{userId}", method = RequestMethod.GET)
	public ServiceResponse<UserModel> updateUser(@PathVariable("userId") Long userId)
			throws ServiceException {
		UserModel userModel = userServiceFacade.getUserModel(userId);
		return new SuccessServiceResponse<UserModel>(userModel);
	}
	
	
	@RequestMapping(path = "/address/{userId}", method = RequestMethod.POST)
	public ServiceResponse<Long> addUserAddress(@PathVariable("userId") Long userId, @RequestBody UserAddressModel userAddress) throws ServiceException{
		
		Long addressId = userServiceFacade.addUserAddress(userId , userAddress);
		return new SuccessServiceResponse<Long>(addressId);
	}
	
 	@RequestMapping(path = "/address/{userId}/{userAddressId}", method = RequestMethod.PUT)
	public ServiceResponse<Long> updateUserAddress(@PathVariable("userId") Long userId,  @PathVariable("userAddressId") Long userAddressId , @RequestBody UserAddressModel userAddress) throws ServiceException{
 		Long addressId = userServiceFacade.updateUserAddress(userId , userAddressId , userAddress);
		return new SuccessServiceResponse<Long>(addressId);


	}
	

	
	@RequestMapping(path = "/address/{userId}", method = RequestMethod.GET)
	public ServiceResponse<List<UserAddressModel>> getUserAddreses(@PathVariable("userId") Long userId) throws ServiceException
 	{
 		List<UserAddressModel> userAddresses = userServiceFacade.getUserAddresses(userId);
		return new SuccessServiceResponse<List<UserAddressModel>>(userAddresses);
	}
	
	
	
	@RequestMapping(path = "/address/{userId}/{userAddressId}", method = RequestMethod.DELETE)
	public ServiceResponse<Long> deleteUserAddress(@PathVariable("userId") Long userId, @PathVariable("userAddressId") Long userAddressId) throws ServiceException{
		Long currentDefaultUserAddress = userServiceFacade.deleteUserAddress(userId , userAddressId);;
		return new SuccessServiceResponse<Long>(currentDefaultUserAddress);
	}
	
	
	
	@RequestMapping(path = "/address/defautAddress/{userId}/{userAddressId}", method = RequestMethod.PUT)
	public ServiceResponse<Long> setUserDefaultAddress(@PathVariable("userId") Long userId,@PathVariable("userAddressId") Long userAddressId) throws ServiceException
 	{
 		 userAddressId = userServiceFacade.setUserDefaultAddress(userId,userAddressId);
		return new SuccessServiceResponse<Long>(userAddressId);
	}
	
}