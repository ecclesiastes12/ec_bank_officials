package com.bankdetails.site.fieldofficer;

import java.util.NoSuchElementException;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankdetails.entity.Officer;


@Service
public class OfficerServiceImpl implements OfficerService{

	private FieldOfficerRespository officerRepo;
	private PasswordEncoder passwordEncoder;
	
	public OfficerServiceImpl(FieldOfficerRespository officerRepo, PasswordEncoder passwordEncoder) {
		super();
		this.officerRepo = officerRepo;
		this.passwordEncoder = passwordEncoder;
	}


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
	
	
	@Override
	public Officer getOfficerEmail(String email) {
		return officerRepo.getOfficerByEmail(email);
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


	@Override
	public boolean isEmailUnique(String email) {
	    Officer officerByEmail =	officerRepo.getOfficerByEmail(email);
		return officerByEmail == null;
	}
}
