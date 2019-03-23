package com.eltaieb.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@EnableAsync
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableResourceServer
@EnableJpaAuditing
@PropertySource(value = {"classpath:db.properties","rabbit-mq.properties"})
public class UserServiceApplication {

  
	public static void main(String[] args) {
		
//		UserAddressEntity userAddressEntity = new UserAddressEntity();
//		userAddressEntity.setBuildingNumber("12");
//		userAddressEntity.setDistict("commercial");
//		userAddressEntity.setStreetName("Bank Markazi");
//		userAddressEntity.setFloorNumber(4);
//		userAddressEntity.setLatitude(23.5970954);
//		userAddressEntity.setLongitude(58.5481861);
//		userAddressEntity.setLocationId(1l);
		
//	System.out.println(new Gson().toJson(userAddressEntity));
		SpringApplication.run(UserServiceApplication.class, args);
	}
	 
	 	

	 
	 
}

