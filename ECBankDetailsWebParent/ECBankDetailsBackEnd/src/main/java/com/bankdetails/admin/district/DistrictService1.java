//package com.bankdetails.admin.district;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.bankdetails.admin.user.UserRepository;
//import com.bankdetails.admin.user.UserServiceImpl;
//import com.bankdetails.admin.user.bankdetails.UserBankDetailsRepository;
//import com.bankdetails.admin.user.bankdetails.UserBankDetailsServiceImpl;
//import com.bankdetails.entity.District1;
//import com.bankdetails.entity.User1;
//import com.bankdetails.entity.UserBankDetails1;
//
//import jakarta.transaction.Transactional;
//
//@Service
//public class DistrictService1 {
//
//	@Autowired private DistrictRespository distRepo;
//	
//	@Autowired private UserBankDetailsServiceImpl userBankDetailsService;
//	
//	@Autowired private UserServiceImpl userService;
//	
//	public List<District1> listDistricts(){
//		return distRepo.findAll();
//	}
//	
//	public District1 getDistrictId(Integer id) throws DistrictNotFoundException {
//		try {
//			return distRepo.findById(id).get();
//		} catch (NoSuchElementException e) {
//			throw new DistrictNotFoundException("Could not find District ID: " + id);
//		}
//	}
//	
//	public District1 addDistrict(District1 district) {
//		return distRepo.save(district);
//	}
//	
////	public District createDistrictWithUserAndBankDetails(District district, User user, UserBankDetails userBankDetails) {
////        
////
////        // Set the district in user and user bank details
////        user.setDistrict(district);
////        userBankDetails.setDistrict(district);
////
////     // Set the user and user bank details in the district
////        district.setUser(user);
////        district.setUserBankDetails(userBankDetails);
////        
////     // Save the district to generate an ID
////        district = distRepo.save(district);
////        
////        // Save the user and user bank details
////       // userService.createUserTable4District(user);
////        userBankDetailsService.createBankDetailsTable4District(userBankDetails);
////
////        return district;
////    }
//	
//	public void deleteDistrict(Integer id) throws DistrictNotFoundException {
//		Long countById = distRepo.countById(id);
//		
//		if(countById == null || countById == 0) {
//			throw new DistrictNotFoundException("Could not find District ID: " + id);
//		}
//		
//		distRepo.deleteById(id);
//	}
//	
////	//for auto creation of users and bank details for other districts
////	@Transactional
////	public void createDistrictWithTables(District district) {
////		distRepo.save(district);
////		
////		//create associated table
////		createUserTableForDistrict(district);
////		createUserBankDetailsTableForDistrict(district);
////	}
////	
////    public void createUserTableForDistrict(District district) {
////		useRepo.createUserTable(district.getId());
////	}
////	
////	public void createUserBankDetailsTableForDistrict(District district) {
////		userBankDetailsRepo.createBankTable(district.getId());
////	}
//}
