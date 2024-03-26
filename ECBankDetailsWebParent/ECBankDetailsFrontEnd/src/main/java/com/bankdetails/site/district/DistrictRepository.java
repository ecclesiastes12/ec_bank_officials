package com.bankdetails.site.district;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankdetails.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer>{

	
}
