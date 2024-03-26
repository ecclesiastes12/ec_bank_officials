package com.bankdetails.admin.officerrole;

import java.util.List;

import com.bankdetails.entity.OfficerRole;

public interface OfficerRoleService {

	public List<OfficerRole> listOfficerRoles();
	public OfficerRole saveOfficerRole(OfficerRole officerRole);
	public OfficerRole findOfficerRoleById(Integer id) throws OfficerRoleNotFoundException;
	public void deleteOfficerRole(Integer id) throws OfficerRoleNotFoundException;
}
