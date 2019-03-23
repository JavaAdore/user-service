package com.eltaieb.microservice.userservice.service.impl;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.eltaieb.microservice.base.exception.ServiceException;
import com.eltaieb.microservice.base.model.ErrorMessageCode;
import com.eltaieb.microservice.userservice.dao.JpaUserDao;
import com.eltaieb.microservice.userservice.entity.BaseUserEntity;
import com.eltaieb.microservice.userservice.service.api.UserService;
import com.eltaieb.microservice.userservice.service.model.UserModel;

@Service
public class UserServiceBean implements UserService {

	private static final Logger log = Logger.getLogger(UserServiceBean.class.getName());

	private JpaUserDao jpaUserDao;

	public UserServiceBean(JpaUserDao jpaUserDao) {
		this.jpaUserDao = jpaUserDao;
	}

	@Override
	public Long add(UserModel userModel) {

		BaseUserEntity baseUserEntity = toUserEntity(userModel);

		baseUserEntity = jpaUserDao.save(baseUserEntity);

		return baseUserEntity.getId();
	}

	private BaseUserEntity toUserEntity(UserModel userModel) {
		BaseUserEntity baseUserEntity = new BaseUserEntity();
		baseUserEntity.setFirstName(userModel.getFirstName());
		baseUserEntity.setLastName(userModel.getLastName());
		baseUserEntity.setBirthDate(userModel.getBirthDate());
		baseUserEntity.setGender(userModel.getGender());
		baseUserEntity.setProfilePictureUrl(userModel.getProfilePictureUrl());
		return baseUserEntity;
	}

 

	@Override
	public Long update(Long userId, UserModel userModel) throws ServiceException {
		Optional<BaseUserEntity> basicUserEntityOptional = jpaUserDao.findById(userId);

		if (basicUserEntityOptional.isPresent()) {
			//TODO use mapping api later on
			BaseUserEntity basicUserEntity = basicUserEntityOptional.get();
			basicUserEntity.setFirstName(userModel.getFirstName());
			basicUserEntity.setLastName(userModel.getLastName());
			basicUserEntity.setBirthDate(userModel.getBirthDate());
			basicUserEntity.setGender(userModel.getGender());
			basicUserEntity.setProfilePictureUrl(userModel.getProfilePictureUrl());
			jpaUserDao.save(basicUserEntity);
			return userId;
		}
		
		throw new ServiceException(ErrorMessageCode.NO_SUCH_USER);
 	}

	@Override
	public Optional<BaseUserEntity> getBaseUserEntity(Long userId) {
		return jpaUserDao.findById(userId);
	}

}
