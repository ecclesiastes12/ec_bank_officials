//package com.bankdetails.site.district.officers;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.OptimisticLockingFailureException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//import com.bankdetails.entity.Officer;
//import com.bankdetails.entity.User;
//
//@Service
//public class OfficerService1 {
//	
//	@Autowired private OfficersRepository officerRepo;
//	@Autowired private PasswordEncoder passwordEncoder;
//	
//	public List<Officer> listOfficerByDistrictId(Integer districtId) {// TODO Auto-generated method stub
//		return officerRepo.findByDistrictId(districtId);
//	}
//
//	public Officer saveOfficer(Officer officer) {
//				// checks if the form is in the edit or new  mode before saving the data
//				boolean isUpdatingUser = (officer.getId() != null);
//
//				if (isUpdatingUser) {
//					//retrieves user details from the database
//					Officer existingOfficer = officerRepo.findById(officer.getId()).get();
//
//					if(!existingOfficer.getVersion().equals(officer.getVersion())) {
//						throw new OptimisticLockingFailureException("This user is being updated by another person");
//					}else {
//					//checks if password is empty
//					//NB if the password is empty it means user want to maintain the old password
//					if(officer.getPassword().isEmpty()) {
//						//reads password from the database and set it to officer object
//						officer.setPassword(existingOfficer.getPassword());
//					}else {
//						//encode the new password if the password field is not empty
//						encodePassword(officer);
//					}
//					}
//				} else {
//					encodePassword(officer); // method call
//				}
//		return officerRepo.save(officer);
//	}
//	
//	public Officer getByEmail(String email) {
//		return officerRepo.getOfficerByEmail(email);
//	}
//
//	public Officer getOfficerId(Integer id) throws OfficerNotFoundException {
//		try {
//			return officerRepo.findById(id).get();
//		} catch (NoSuchElementException e) {
//			throw new OfficerNotFoundException("Could not find user ID: " + id);
//		}
//	}
//	
//	
//	//method that update user status
//		public void updateOfficerStatus(Integer id, boolean enabled) {
//			officerRepo.updateStatus(id, enabled);
//			
//		}
//
//		
//		public void encodePassword(Officer officer) {
//			String encodePassword = passwordEncoder.encode(officer.getPassword());
//			officer.setPassword(encodePassword);
//		}
//
//		
//		public boolean isEmailUnique(Integer id, String email) {
//			//retrieve user email from the database
//			Officer officerByEmail = officerRepo.getOfficerByEmail(email);
//			
//			//check if user email exist in the db
//			if(officerByEmail == null)
//				return true;// if it returns null meaning email is unique in the db
//			
//			//check if the form is in the new or edit mode
//			// if id is null meaning the form is in the new mode
//			// if id is not null meaning the form is in the edit mode
//			boolean isCreatingNew = (id == null);
//			
//			if(isCreatingNew) {
//				// if email is not null meaning the email exist in the db
//				// therefore the email is not unique. New email must be provided
//				if(officerByEmail != null) {
//					return false;
//				}
//			}else {
//				// if the ID of the user find by email is different from the ID
//				// of the user being edited then email is not unique
//				if(officerByEmail.getId() != id) {
//					return false;
//				}
//			}
//			return true;
//		}
//	
//	
//	
//	
//	
//}
