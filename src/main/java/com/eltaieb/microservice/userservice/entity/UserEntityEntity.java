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
@Entity(name = "UserEntityEntity")
@Table(name="user_entity")
public class UserEntityEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_entity_pk_seq")
	@SequenceGenerator(name="user_entity_pk_seq" , sequenceName="user_entity_pk_seq",allocationSize=1)
	private Long id;

	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private BaseUserEntity user;
	
	
	@Column(name = "entity_id")
	private Long entityId;
 

}
