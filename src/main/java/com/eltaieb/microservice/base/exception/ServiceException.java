package com.eltaieb.microservice.base.exception;

import com.eltaieb.microservice.base.model.ErrorMessageCode;

import lombok.Getter;

@Getter
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private ErrorMessageCode errorMessageCode;
	private Object[] params;
 	
	public ServiceException(ErrorMessageCode errorMessageCode) {
		this.errorMessageCode = errorMessageCode;
	}
	public ServiceException(ErrorMessageCode errorMessageCode,Object[] params) {
		this(errorMessageCode);
		this.params=params;
	}
	
	public String getCode()
	{
		if(null !=errorMessageCode)
		{
			return errorMessageCode.getCode();
		}
		return null;
	}
	
	
	public String getMessageKey()
	{
		if(null !=errorMessageCode)
		{
			return errorMessageCode.getMessageKey();
		}
		return null;
	}
}
