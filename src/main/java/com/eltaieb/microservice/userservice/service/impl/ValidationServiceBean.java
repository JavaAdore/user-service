package com.eltaieb.microservice.userservice.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.base.model.ErrorMessageCode;
import com.eltaieb.microservice.base.util.Utils;
import com.eltaieb.microservice.userservice.dao.JpaUserAddressDao;
import com.eltaieb.microservice.userservice.dao.JpaUserDao;
import com.eltaieb.microservice.userservice.entity.UserAddressEntity;
import com.eltaieb.microservice.userservice.service.api.ValidationService;
import com.eltaieb.microservice.userservice.service.model.UserAddressModel;
import com.eltaieb.microservice.userservice.service.model.UserModel;
import com.eltaieb.microservice.userservice.validation.ValidationConstants;

@Service
public class ValidationServiceBean implements ValidationService {
	
	private JpaUserDao jpaUserDao;
	private JpaUserAddressDao jpaUserAddressDao;
	public ValidationServiceBean(JpaUserDao jpaUserDao , JpaUserAddressDao jpaUserAddressDao)
	{
		this.jpaUserDao=jpaUserDao;
		this.jpaUserAddressDao=jpaUserAddressDao;
	}

	@Override
	public void validateRegisterNewPublicUser(UserModel userModel) throws ServiceException {
 		validateUserFirstName(userModel.getFirstName());
 		validateUserLastName(userModel.getFirstName());
 		validateUserBirthdate(userModel.getBirthDate());
	}

	private void validateUserLastName(String firstName) throws ServiceException {
		validateUserLastNameExistance(firstName);
		validateUserLastNameSpecialCharacters(firstName);		
	}

	private void validateUserLastNameSpecialCharacters(String lastName) throws ServiceException {
			
	}

	private void validateUserLastNameExistance(String lastName) throws ServiceException {
		if(StringUtils.isEmpty(lastName))
		{
			throw new ServiceException(ErrorMessageCode.PUBLIC_USER_LAST_NAME_IS_REQIORED, new Object[] {ValidationConstants.MIN_ALLOWED_PUBLIC_USER_AGE});
		}			
	}

	private void validateUserFirstName(String firstName) throws ServiceException {
		validateUserFirstNameExistance(firstName);
		validateUserFirstNameSpecialCharacters(firstName);
	}

	private void validateUserFirstNameSpecialCharacters(String firstName) {
		 
	}

	private void validateUserFirstNameExistance(String firstName) throws ServiceException {
		if(StringUtils.isEmpty(firstName))
		{
			throw new ServiceException(ErrorMessageCode.PUBLIC_USER_FIRST_NAME_IS_REQIORED, new Object[] {ValidationConstants.MIN_ALLOWED_PUBLIC_USER_AGE});
		}		
	}

	 
		

	private void validateUserBirthdate(LocalDate birthDate) throws ServiceException {
		if(null !=birthDate) {
		validateIfUserDataInTheFuture(birthDate);
		validateUserMinAge(birthDate);
		}
	
		
	}

	private void validateUserMinAge(LocalDate birthDate) throws ServiceException {
		 if( calcualteUserAge(birthDate) < ValidationConstants.MIN_ALLOWED_PUBLIC_USER_AGE)
		 {
				throw new ServiceException(ErrorMessageCode.PUBLIC_USER_AGE_IS_NOT_ALLOWED, new Object[] {ValidationConstants.MIN_ALLOWED_PUBLIC_USER_AGE});
		 }		
	}

	private void validateIfUserDataInTheFuture(LocalDate birthDate) throws ServiceException {
		if(birthDate.isAfter(LocalDate.now()))
		{
			throw new ServiceException(ErrorMessageCode.PUBLIC_USER_BIRTHDAY_COULD_NOT_BE_IN_THE_FUTURE);
		}		
	}

	private int calcualteUserAge(LocalDate birthDate) {
  		return (int) java.time.temporal.ChronoUnit.YEARS.between( birthDate , LocalDate.now() );

	}

	@Override
	public void validateUpdateUserProfilePictureUrl(Long userId, String url) throws ServiceException {
		validateUserId(userId);
		validateProfileImageUrk(url);
	}

	private void validateProfileImageUrk(String url) throws ServiceException {
		if(null != url) {
		valdiateUrlFormat(url);
		}
	}

	private void valdiateUrlFormat(String url) throws ServiceException {
		
	boolean isValid=	Utils.isValidUrl(url);
		if(Boolean.FALSE==isValid)
		{
			throw new ServiceException(ErrorMessageCode.INVALID_PROFILE_PICTURE_URL_FORMAT);

		}
	}

	private void validateUrlExistance(String url) throws ServiceException {
		if(StringUtils.isEmpty(url))
		{
			throw new ServiceException(ErrorMessageCode.NO_IMAGE_URL_PROVIDED);
		}		
	}

	private void validateUserId(Long userId) throws ServiceException {
		if(null == userId)
		{
			throw new ServiceException(ErrorMessageCode.NO_USER_ID_PROVIDED);
		}	
		if(0 > userId)
		{
			throw new ServiceException(ErrorMessageCode.USER_ID_MUST_BE_POSITIVE_VALUE);

		}
	}

	@Override
	public void validateAddUserAddress(Long userId, UserAddressModel userAddress) throws ServiceException {
		validateUserAddress(userAddress);
 	}

	@Override
	public void validateUpdateUserAddress(Long userId, Long userAddressId, UserAddressModel userAddress) throws ServiceException {
		validateUserAddress(userId, userAddressId);
		validateUserAddress(userAddress);
	}

	private void validateUserAddress(UserAddressModel userAddress) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	private void validateUserAddress(Long userId, Long userAddressId) throws ServiceException {
		validateUserId(userId);
		validateUserExistance(userId);
		validateAddressPresent(userAddressId);
		validateAddressExistance(userAddressId);
		validateAddressBelongToUser(userId , userAddressId);		
	}

	private void validateAddressBelongToUser(Long userId, Long userAddressId) throws ServiceException {
		Optional<UserAddressEntity> userAddressOptional  = jpaUserAddressDao.findById(userAddressId);
		UserAddressEntity userAddressEntity = userAddressOptional.get();
		boolean belongToUser = userAddressEntity.getUser().getId().equals(userId);
		if(Boolean.FALSE==belongToUser)
		{
			throw new ServiceException(ErrorMessageCode.PROVIDED_ADDRESS_DOESNOT_BELONG_TO_USER);

		}
	}

	private void validateAddressExistance(Long userAddressId) throws ServiceException {
		boolean exist =jpaUserAddressDao.existsById(userAddressId);
		if(Boolean.FALSE == exist) {
			throw new ServiceException(ErrorMessageCode.PROVIDED_ADDRESS_ID_DOESNT_EXIST);
			}
	}

	private void validateAddressPresent(Long userAddressId) throws ServiceException {
		if(null ==userAddressId )
		{
			throw new ServiceException(ErrorMessageCode.NO_ADDRESS_ID_PROVIDED);

		}
	}

	private void validateUserExistance(Long userId) throws ServiceException {
		boolean exist = jpaUserDao.existsById(userId);
		if(Boolean.FALSE == exist) {
		throw new ServiceException(ErrorMessageCode.PROVIDED_USER_DOESNT_EXIST);
		}
	}

	@Override
	public void validateDeleteUserAddress(Long userId, Long userAddressId) throws ServiceException {
		validateUserAddress(userId, userAddressId);
	}

	@Override
	public void validateGetUserAddresses(Long userId) throws ServiceException {
		
		validateUser(userId);
		
	}

	private void validateUser(Long userId) throws ServiceException {
		validateUserId(userId);
		validateUserExistance(userId);
	}

	@Override
	public void validateSetUserDefaultAddress(Long userId, Long userAddressId) throws ServiceException {
		validateUser(userId);	
		validateUserAddress(userId, userAddressId);
	}

	@Override
	public void validateGetUserModel(Long userId) throws ServiceException {
		validateUser(userId);
	}

	@Override
	public void validateAssignUserToEntity(Long userId, Long entityId) throws ServiceException {
		// TODO Auto-generated method stub
		
	}
 

	 

	 
	  

}
