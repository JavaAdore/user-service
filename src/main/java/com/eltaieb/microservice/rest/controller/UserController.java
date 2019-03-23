package com.eltaieb.microservice.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eltaieb.microservice.base.aspect.SecurityUtilityBean;
import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.base.footprint.FootPrint;
import com.eltaieb.microservice.base.model.ServiceResponse;
import com.eltaieb.microservice.base.model.SuccessServiceResponse;
import com.eltaieb.microservice.userservice.facade.UserServiceFacade;
import com.eltaieb.microservice.userservice.service.model.ChangePasswordModel;
import com.eltaieb.microservice.userservice.service.model.UserAddressModel;
import com.eltaieb.microservice.userservice.service.model.UserModel;

@RestController()
@RequestMapping("user")
 public class UserController {
	
	private UserServiceFacade userServiceFacade;
	
	public UserController(UserServiceFacade userServiceFacade)
	{
		this.userServiceFacade=userServiceFacade;
	}
   
	 
 

 	@RequestMapping(path ="/" , method=RequestMethod.PUT)
 	public ServiceResponse<Long> updateUser( @RequestBody UserModel user) throws ServiceException
	{
 		Long userId = SecurityUtilityBean.loadCurrentUserId();
 		userId  =userServiceFacade.updateUser(userId,user);
		return new SuccessServiceResponse<Long>(userId);
   

	}
 	
 	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ServiceResponse<UserModel> updateUser()
			throws ServiceException {
 		Long userId = SecurityUtilityBean.loadCurrentUserId();
		UserModel userModel = userServiceFacade.getUserModel(userId);
		return new SuccessServiceResponse<UserModel>(userModel);
	}
 	 
 	
 	@RequestMapping(path = "/address/", method = RequestMethod.POST)
	public ServiceResponse<Long> addUserAddress( @RequestBody UserAddressModel userAddress) throws ServiceException
 	{
 		Long userId = SecurityUtilityBean.loadCurrentUserId();
		Long addressId = userServiceFacade.addUserAddress(userId , userAddress);
		return new SuccessServiceResponse<Long>(addressId);


	}
 	

 	@RequestMapping(path = "/address/{userAddressId}", method = RequestMethod.PUT)
	public ServiceResponse<Long> updateUserAddress( @PathVariable("userAddressId") Long userAddressId , @RequestBody UserAddressModel userAddress) throws ServiceException{
 		Long userId = SecurityUtilityBean.loadCurrentUserId();
		Long addressId = userServiceFacade.updateUserAddress(userId , userAddressId , userAddress);
		return new SuccessServiceResponse<Long>(addressId);


	}
 	
 	@FootPrint("GET_MY_ADDRESSES")
	@RequestMapping(path = "address/", method = RequestMethod.GET)
	public ServiceResponse<List<UserAddressModel>> getUserAddreses() throws ServiceException
 	{
 		Long userId = SecurityUtilityBean.loadCurrentUserId();
 		List<UserAddressModel> userAddresses = userServiceFacade.getUserAddresses(userId);
		return new SuccessServiceResponse<List<UserAddressModel>>(userAddresses);
	}
	
	
	@RequestMapping(path = "address/{userAddressId}", method = RequestMethod.DELETE)
	public ServiceResponse<Long> deleteUserAddress(  @PathVariable("userAddressId") Long userAddressId) throws ServiceException{
 		Long userId = SecurityUtilityBean.loadCurrentUserId();
		Long currentDefaultUserAddress = userServiceFacade.deleteUserAddress(userId , userAddressId);;
		return new SuccessServiceResponse<Long>(currentDefaultUserAddress);
	}
	
	
	
	@RequestMapping(path = "address/defautAddress/{userAddressId}", method = RequestMethod.PUT)
	public ServiceResponse<Long> setUserDefaultAddress(@PathVariable("userAddressId") Long userAddressId) throws ServiceException
 	{
 		Long userId = SecurityUtilityBean.loadCurrentUserId();
 		userAddressId = userServiceFacade.setUserDefaultAddress(userId,userAddressId);
		return new SuccessServiceResponse<Long>(userAddressId);
	}
	
 	@FootPrint("CHANGE_MY_PASSWORD")
 	@RequestMapping(path ="/password/change/" , method=RequestMethod.POST)
 	public ServiceResponse<Object> changePassword( @RequestBody ChangePasswordModel changePasswordModel)
	{
 		
 		Long loginUserId = SecurityUtilityBean.loadCurrentUserLoginId();
 		userServiceFacade.changePassword(loginUserId , changePasswordModel);
		return new SuccessServiceResponse<Object>("");

	}
	 
	
	
	
	
}



	 

	
	
	
	 
