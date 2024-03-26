package com.bankdetails.admin.user.bankdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bankdetails.entity.UserBankDetails;

public interface UserBankDetailsRepository extends JpaRepository<UserBankDetails, Integer>{

	Long countById(Integer id);
	
	//for auto creation of userbank details for a district
	@Query(value = "CREATE TABLE IF NOT EXISTS bank_district_?1 (id BIGINT AUTO_INCREMENT PRIMARY KEY, bank_name VARCHAR(255), district_id BIGINT, FOREIGN KEY (district_id) REFERENCES district(id))", nativeQuery = true)
    void createBankTable(Integer districtId);
}
