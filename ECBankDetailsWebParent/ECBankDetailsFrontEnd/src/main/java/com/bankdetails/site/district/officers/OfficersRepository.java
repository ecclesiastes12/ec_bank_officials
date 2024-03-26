package com.bankdetails.site.district.officers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankdetails.entity.Officer;
public interface OfficersRepository extends JpaRepository<Officer, Integer>{

	@Query("UPDATE Officer o SET o.enabled = ?2 WHERE o.id = ?1")
	@Modifying
	public void updateStatus(Integer id, boolean enabled);

	//method that get user by email
	@Query("SELECT oc FROM Officer oc WHERE oc.email = :email")
	Officer getOfficerByEmail(@Param("email") String email);
	
	List<Officer> findByDistrictId( Integer districtId);
}
