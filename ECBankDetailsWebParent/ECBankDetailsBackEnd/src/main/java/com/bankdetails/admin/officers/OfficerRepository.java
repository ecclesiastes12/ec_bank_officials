package com.bankdetails.admin.officers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bankdetails.entity.Officer;

public interface OfficerRepository extends JpaRepository<Officer, Integer>{

	@Query("UPDATE Officer o SET o.enabled = ?2 WHERE o.id = ?1")
	@Modifying
	public void updateStatus(Integer id, boolean enabled);
}
