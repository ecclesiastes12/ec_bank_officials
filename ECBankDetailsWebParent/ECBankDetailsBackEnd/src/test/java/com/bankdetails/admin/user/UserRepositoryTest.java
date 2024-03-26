package com.bankdetails.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bankdetails.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired UserRepository userRepo;
	
	@Test
	public void testAddUser() {
		User user = new User("Peter","Owusu","peter@gmail.com","12345678","2345675436",null);
		User savedUser = userRepo.save(user);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListUsers() {
		List<User> listUsers = userRepo.findAll();
		listUsers.forEach(System.out :: println);
	}
	
	@Test
	public void testGetUserById() {
		Integer id = 1;
		User user = userRepo.findById(id).get();
		
		assertThat(user.getId()).isNotNull();
		
	}
}
