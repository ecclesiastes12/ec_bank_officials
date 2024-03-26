package com.bankdetails.admin.officerrole;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.bankdetails.entity.OfficerRole;

@Service
public class OfficerRoleServiceImpl implements OfficerRoleService{
	
	private OfficerRoleRepository officerRoleRepo;
	
	public OfficerRoleServiceImpl(OfficerRoleRepository officerRoleRepo) {
		this.officerRoleRepo = officerRoleRepo;
	}

	@Override
	public List<OfficerRole> listOfficerRoles() {
		// TODO Auto-generated method stub
		return (List<OfficerRole>) officerRoleRepo.findAll();
	}

	@Override
	public OfficerRole saveOfficerRole(OfficerRole officerRole) {
		// TODO Auto-generated method stub
		return officerRoleRepo.save(officerRole);
	}

	@Override
	public void deleteOfficerRole(Integer id) throws OfficerRoleNotFoundException {
		Long countById = officerRoleRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new OfficerRoleNotFoundException("Could not find Officer Role ID: " + id);
		}
		
		officerRoleRepo.deleteById(id);
	}

	@Override
	public OfficerRole findOfficerRoleById(Integer id) throws OfficerRoleNotFoundException {
		// TODO Auto-generated method stub
		try {
			return officerRoleRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			throw new OfficerRoleNotFoundException("Could not find Officer role ID: " + id);
		}
		
	}

}
