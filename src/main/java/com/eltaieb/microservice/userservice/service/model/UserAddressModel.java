package com.eltaieb.microservice.userservice.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAddressModel {

	private Long id;
	
 	private Long locationId;

 	private String distict;

 	private String buildingNumber;

 	private String streetName;

 	private Integer floorNumber;
	
 	private Double longitude;
	
 	private Double latitude;
	
 	private Boolean defaultAddress;
}
