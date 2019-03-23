package com.eltaieb.microservice.base.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 import com.eltaieb.microservice.base.feignclient.config.ContentExtractorFeignConfiuration;
import com.eltaieb.microservice.userservice.service.model.ChangePasswordModel;

 
@FeignClient(name="AUTHORIZATION-SERVER",configuration=ContentExtractorFeignConfiuration.class)
public interface UserLoginService {

 	@RequestMapping(path ="password/change/{userLoginId}" , method=RequestMethod.POST)
	String changePassword( @PathVariable("userLoginId") Long userLoginId, @RequestBody ChangePasswordModel changePasswordModel);
	 
}
