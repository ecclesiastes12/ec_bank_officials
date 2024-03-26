package com.bankdetails.admin.officers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankdetails.entity.Officer;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OfficerServiceImpl implements OfficerService{

	private OfficerRepository officerRepo;
	
	@Autowired
	public OfficerServiceImpl(OfficerRepository officerRepo) {
		this.officerRepo = officerRepo;
	}

	@Override
	public List<Officer> listOfficers() {
		return officerRepo.findAll();
	}

	@Override
	public void updateOfficerStatus(Integer id, boolean enabled) {
		officerRepo.updateStatus(id, enabled);
		
	}

//	public Officer getOfficerById(Integer id) throws OfficerNotFoundException {
//		
//		try {
//			return officerRepo.findById(id).get();
//		} catch (NoSuchElementException e) {
//			throw new OfficerNotFoundException("Could not find officer ID: " + id);
//		}
//	}

}
