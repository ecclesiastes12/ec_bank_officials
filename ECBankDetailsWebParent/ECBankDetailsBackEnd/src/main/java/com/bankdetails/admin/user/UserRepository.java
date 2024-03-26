package com.bankdetails.admin.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankdetails.entity.District;
import com.bankdetails.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Long countById(Integer id);

	//enable and disabled status
	
	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
	@Modifying
	public void updateStatus(Integer id, boolean enabled);

	//method that get user by email
	@Query("SELECT u FROM User u WHERE u.email = :email")
	User getUserByEmail(@Param("email") String email);
	
	
	List<User> findByDistrictId( Integer districtId);

	int countByDistrictId(int districtId);
	
	
	
}
