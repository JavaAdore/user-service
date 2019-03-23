package com.eltaieb.microservice.base.security.jwt;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import com.eltaieb.microservice.base.model.UserLogin;

 
@Component
public class JwtTokenConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

	@Override
	public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {

		jwtAccessTokenConverter.setAccessTokenConverter(this);
	}

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
		OAuth2Authentication auth2Authentication = super.extractAuthentication(map);

		Integer userId = (Integer) map.get("userId");

		Integer userLoginId = (Integer) map.get("userLoginId");

		UserLogin userLogin = new UserLogin();
		if (null != userId) {
			userLogin.setUserId(new Long(userId));
		}

		if (null != userLoginId) {
			userLogin.setUserLoginId(new Long(userLoginId));
		}

		auth2Authentication.setDetails(userLogin);

		return auth2Authentication;
	}
}