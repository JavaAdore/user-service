package com.eltaieb.microservice.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.base.model.ServiceResponse;
import com.eltaieb.microservice.base.model.SuccessServiceResponse;
import com.eltaieb.microservice.userservice.facade.UserServiceFacade;

@RestController()
@RequestMapping("system-user-administration")
public class SystemUserController {
	
	private UserServiceFacade userServiceFacade;
	
	public SystemUserController(UserServiceFacade userServiceFacade)
	{
		this.userServiceFacade=userServiceFacade;
	}
	
	
	
	@RequestMapping(path ="/add/{userName}" , method=RequestMethod.GET)
	public ServiceResponse<Long> addNewSystemUser(@PathVariable("userName") String userName) throws ServiceException
	{
		Long userId = userServiceFacade.addNewSystemUser(userName);
		return new SuccessServiceResponse<Long>(userId);
	}
 	
	@RequestMapping(path ="/assign/{userId}/{entityId}" , method=RequestMethod.POST)
	ServiceResponse<Long> assignUserToEntity(@PathVariable("userId") Long userId,@PathVariable("entityId")  Long entityId) throws ServiceException
	{
		Long userEntityId = userServiceFacade.assignUserToEntity(userId,entityId);
		return  new SuccessServiceResponse<Long>(userEntityId);
	}
}
