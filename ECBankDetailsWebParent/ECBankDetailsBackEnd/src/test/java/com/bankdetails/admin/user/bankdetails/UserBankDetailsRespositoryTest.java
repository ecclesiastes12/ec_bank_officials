package com.bankdetails.admin.user.bankdetails;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bankdetails.entity.UserBankDetails;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserBankDetailsRespositoryTest {

	@Autowired UserBankDetailsRepository bankDetailsRepo;
	
	@Test
	public void testListUserBankDetails() {
		List<UserBankDetails> listAll = bankDetailsRepo.findAll();
		listAll.forEach(System.out :: println);
		
		assertThat(listAll).isNotNull();
	}
	
	@Test
	public void testaddUserBankDetails() {
		UserBankDetails userBankDetails = new UserBankDetails("Martin", "Opari", "3422333465", "Ga Rural Bank", "Amasaman");
		UserBankDetails bankDetails = bankDetailsRepo.save(userBankDetails);
		
		assertThat(bankDetails).isNotNull();
		assertThat(bankDetails.getId()).isGreaterThan(0);
	}
	
	
	@Test
	public void testGetUserBankDetailsId() {
		UserBankDetails bankDetailsId = bankDetailsRepo.findById(1).get();
		
		System.out.println(bankDetailsId);
		assertThat(bankDetailsId.getId()).isNotNull();
		assertThat(bankDetailsId.getId()).isGreaterThan(0);
		
	}
}
