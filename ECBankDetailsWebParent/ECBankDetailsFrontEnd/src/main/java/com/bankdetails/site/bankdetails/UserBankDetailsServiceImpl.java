package com.bankdetails.site.bankdetails;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.bankdetails.entity.UserBankDetails;

@Service
public class UserBankDetailsServiceImpl implements UserBankDetailsServiceInterface{

	private UserBankDetailsRepository userBankDetailsRepo;
	
	public UserBankDetailsServiceImpl(UserBankDetailsRepository userBankDetailsRepo) {
		this.userBankDetailsRepo = userBankDetailsRepo;
	}
	
	@Override
	public List<UserBankDetails> listUserBankDetails() {
		// TODO Auto-generated method stub
		return userBankDetailsRepo.findAll();
	}

	@Override
	public UserBankDetails addUserBankDetails(UserBankDetails userBankDetails) {
		// TODO Auto-generated method stub
		return userBankDetailsRepo.save(userBankDetails);
	}
	
	

//	@Override
//	public UserBankDetails createBankDetailsTable4District(UserBankDetails userBankDetails) {
//		// TODO Auto-generated method stub
//		return userBankDetailsRepo.save(userBankDetails);
//	}

	@Override
	public UserBankDetails getBankDetailsById(Integer id) throws UserBankDetailsNotFoundException {
		try {
			// TODO Auto-generated method stub
			return userBankDetailsRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new UserBankDetailsNotFoundException("Could not find user bank details ID: " + id);
		}
	}

	@Override
	public void deleteUserBankDetails(Integer id) throws UserBankDetailsNotFoundException {
		
		Long countById = userBankDetailsRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new UserBankDetailsNotFoundException("Could not find user bank details ID: " + id);
		}
		userBankDetailsRepo.deleteById(id);
	}

	@Override
	public UserBankDetails getOfficerIdInUserBankDetails(Integer id) {
		
		return userBankDetailsRepo.findUserBankDetailsById(id);
	}

}
