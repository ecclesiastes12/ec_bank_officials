package com.bankdetails.admin.district;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankdetails.entity.District;

public interface DistrictRespository extends JpaRepository<District, Integer>{

	Long countById(Integer id);
	
	

}
