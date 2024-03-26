package com.bankdetails.admin.officers;

import java.util.List;

import com.bankdetails.entity.Officer;

public interface OfficerService {
	
	public List<Officer> listOfficers();
	public void updateOfficerStatus(Integer id, boolean enabled);
//	public Officer getOfficerById(Integer id) throws OfficerNotFoundException;

}
