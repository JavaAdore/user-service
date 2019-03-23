package com.eltaieb.microservice.userservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "UserAddress")
@Table(name = "user_address")
public class UserAddressEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_address_pk_seq")
	@SequenceGenerator(name="user_address_pk_seq" , sequenceName="user_address_pk_seq",allocationSize=1)
	private Long id;

	@Column(name = "location_id")
	private Long locationId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_Id", referencedColumnName = "id")
	private BaseUserEntity user;

	@Column(name = "distict")
	private String distict;

	@Column(name = "building_number")
	private String buildingNumber;

	@Column(name = "street_name")
	private String streetName;

	@Column(name = "floor_number")
	private Integer floorNumber;
	
	
	@Column(name="longitude")
	private Double longitude;
	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="is_default_address")
	private Boolean defaultAddress;
	
	@Column(name="is_deleted")
	private Boolean deleted;
}
