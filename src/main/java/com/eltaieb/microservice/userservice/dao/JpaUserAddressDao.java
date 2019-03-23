package com.eltaieb.microservice.userservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.eltaieb.microservice.userservice.entity.UserAddressEntity;

public interface JpaUserAddressDao extends CrudRepository<UserAddressEntity,Long> {

	@Query("select ua from UserAddress ua where ua.user.id = :#{#userId} and ua.deleted =:#{T(java.lang.Boolean).FALSE} " )
	public List<UserAddressEntity> getUserAddresses(@Param("userId") Long userId);
 
	@Modifying
	@Query("update UserAddress ua set ua.defaultAddress =:#{T(java.lang.Boolean).FALSE} where ua.user.id= :#{#userId} and ua.deleted =:#{T(java.lang.Boolean).FALSE} ")
	public void removeDefaultAddressFlagForUser(@Param("userId") Long userId);
	
	@Query("select ua from UserAddress ua where ua.defaultAddress =:#{T(java.lang.Boolean).TRUE} and ua.user.id= :#{#userId}")
	public Optional<UserAddressEntity> findUserDefaultAddress(@Param("userId") Long userId);
	
	
	
 
}
