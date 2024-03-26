package com.bankdetails.site.district.officersrole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankdetails.entity.OfficerRole;

@Service
public class OfficerRoleService {

	@Autowired private OfficerRoleRepository officerRoleRepo;
	
	public List<OfficerRole> listOfficerRoles() {
		return (List<OfficerRole>) officerRoleRepo.findAll();
	}
}
