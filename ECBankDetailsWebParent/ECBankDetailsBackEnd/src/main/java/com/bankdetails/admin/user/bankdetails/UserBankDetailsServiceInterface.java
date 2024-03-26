package com.bankdetails.admin.user.bankdetails;

import java.util.List;

import com.bankdetails.entity.UserBankDetails;

public interface UserBankDetailsServiceInterface {

	public List<UserBankDetails> listUserBankDetails();
	public UserBankDetails addUserBankDetails(UserBankDetails userBankDetails);
	//public UserBankDetails createBankDetailsTable4District(UserBankDetails userBankDetails);
	public UserBankDetails getBankDetailsById(Integer id) throws UserBankDetailsNotFoundException;
	public void deleteUserBankDetails(Integer id) throws UserBankDetailsNotFoundException;
}
