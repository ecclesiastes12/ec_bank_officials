package com.bankdetails.site.fieldofficer;

import com.bankdetails.entity.Officer;

public interface OfficerService {

	public Officer addOfficer(Officer officer);
	public Officer getOfficerEmail(String email);
	public Officer getOfficerId(Integer id) throws OfficerNotFoundException;
	public void updateOfficerStatus(Integer id, boolean enabled);
	public boolean isEmailUnique(String email);
}
