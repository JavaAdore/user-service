package com.eltaieb.microservice.base.aspect;

import java.util.Locale;
import java.util.logging.Level;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.eltaieb.microservice.base.exception.LocalizedServiceException;
import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.base.model.ErrorMessageCode;
import com.eltaieb.microservice.base.model.ServiceResponse;
import com.eltaieb.microservice.base.model.SuccessServiceResponse;

import lombok.extern.java.Log;
@Log
@Aspect
@Service
public class ServiceRestControllerAspect {

	@Autowired
	SecurityUtilityBean securityUtilityBean;
	@Autowired
	private MessageSource messageSource;
	
	@SuppressWarnings("rawtypes")
	@Around("execution(* com.eltaieb.microservice.rest.controller.*.*(..))")
	public Object enhanceResponse(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			Object obj = joinPoint.proceed();
			if (obj instanceof SuccessServiceResponse) {
				setFeedMessageAsPerRequesterLocale((SuccessServiceResponse) obj);
			} 
			return obj;
		} catch (Exception ex) {
 
			ServiceException serviceException = null;
			if (ex instanceof ServiceException) {
				 serviceException = (ServiceException) ex;
 
			}else  if(ex instanceof LocalizedServiceException)
			{
				LocalizedServiceException localizedServiceException = (LocalizedServiceException) ex;
				return new ServiceResponse<>(localizedServiceException.getCode(), ex.getMessage());
			}
 			else
 			{
 				log.log(Level.WARNING,ex.getMessage(),ex);
 				serviceException = new ServiceException(ErrorMessageCode.GENERAL_BACKEND_ERROR);
 			}
			
			return prepareFailureRestServiceResponse(serviceException);

		}
	}

	@SuppressWarnings("rawtypes")
	private void setFeedMessageAsPerRequesterLocale(SuccessServiceResponse successServiceResponse) {
		String messageKey = successServiceResponse.getMessage();
		String message = getMessage(messageKey); 
		successServiceResponse.setMessage(message);
	}

	private String getMessage(String messageKey, Object... params) {
		try {
			Locale requesterLocale = securityUtilityBean.getRequesterLocale();

			return messageSource.getMessage(messageKey, params, requesterLocale);

		} catch (Exception ex) {
			return messageKey;
		}
	}

	private Object prepareFailureRestServiceResponse(ServiceException serviceException) {

		String message = getMessage(serviceException.getMessageKey(), serviceException.getParams());

		return new ServiceResponse<>(serviceException.getCode(), message);

	}
	
	 

}
