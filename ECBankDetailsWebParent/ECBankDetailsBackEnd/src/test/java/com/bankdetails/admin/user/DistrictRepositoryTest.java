package com.bankdetails.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bankdetails.admin.district.DistrictRespository;
import com.bankdetails.entity.District;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class DistrictRepositoryTest {

	
	@Autowired DistrictRespository distRepo;
	
	@Test
	public void testAddUser() {
		District user = new District("C05","GA West", "Greater Accra");
		District savedUser = distRepo.save(user);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
}
