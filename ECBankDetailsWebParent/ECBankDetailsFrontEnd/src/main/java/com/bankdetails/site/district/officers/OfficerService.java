package com.bankdetails.site.district.officers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankdetails.entity.Officer;

@Service
public class OfficerService {

	@Autowired PasswordEncoder passwordEncoder;
	@Autowired private OfficersRepository officerRepo;
	
	//business method that add officer
	public Officer addOfficer(Officer officer) {
		//check if the form is in the edit or new mode before saving the data
		boolean isUpdatingOfficer = officer.getId() != null;
		
		if(isUpdatingOfficer) {
			//retrieve the existing officer details
			Officer existingOfficer = officerRepo.findById(officer.getId()).get();
			
			if(!existingOfficer.getVersion().equals(existingOfficer.getVersion())) {
				throw new OptimisticLockingFailureException("This user is being updated by another person");
			}else {
				if(officer.getPassword().isEmpty()) {
					officer.setPassword(existingOfficer.getPassword());
				}else {
					encodePassword(officer);
				}
			}
		}else {
			encodePassword(officer);
		}
		
		return officerRepo.save(officer);
	}

	
	//business method that check for duplicate email
	public boolean isEmailUnique(Integer id, String email) {
		//retrieve officer email from the database
		Officer isOfficerEmailInDB = officerRepo.getOfficerByEmail(email);
		
		//if it returns null, it means email is unique
		if(isOfficerEmailInDB == null) return true;
		
		
		//check if the form is in the new or edit mode
		// if id is null meaning the form is in the new mode
		// if id is not null meaning the form is in the edit mode
		boolean isCreatingNew = id == null;
		if(isCreatingNew) {
			// if email is not null meaning the email exist in the db
			// therefore the email is not unique. New email must be provided
			if(isOfficerEmailInDB != null) {
				return false;
			}
		}else {
			// if the ID of the user find by email is different from the ID
			// of the user being edited then email is not unique
			if(isOfficerEmailInDB.getId() != id) {
				return false;
			}
		}
		
		return true;
		
	}
	
	private void encodePassword(Officer officer) {
		String encodePassword = passwordEncoder.encode(officer.getPassword());
		officer.setPassword(encodePassword);
		
	}


	public Officer getOfficerId(Integer id) throws OfficerNotFoundException {
		try {
			return officerRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new OfficerNotFoundException("Could not find user ID: " + id);
		}
		
	}
	
	
	//method that update user status
	public void updateOfficerStatus(Integer id, boolean enabled) {
		officerRepo.updateStatus(id, enabled);
		
	}
}
