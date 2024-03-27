package com.bankdetails.site.fieldofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankdetails.entity.Officer;

public interface FieldOfficerRespository extends JpaRepository<Officer, Integer>{

	@Query("SELECT o From Officer o WHERE o.email = :email")
	public Officer getOfficerByEmail(@Param("email") String email);
	
	@Query("UPDATE Officer o SET o.enabled = ?2 WHERE o.id = ?1")
	@Modifying
	public void updateStatus(Integer id, boolean enabled);
	
	List<Officer> findByDistrictId( Integer districtId);
}
