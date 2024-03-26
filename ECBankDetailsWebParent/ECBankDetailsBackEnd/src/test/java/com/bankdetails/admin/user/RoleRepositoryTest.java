package com.bankdetails.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bankdetails.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace =  Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

	
	@Autowired RoleRepository repo;
	
	@Test
	public void testAddRole() {
		Role role = new Role("Field_Officer","Ec representative at a polling center");
		Role saveRole = repo.save(role);
		
		assertThat(saveRole.getId()).isGreaterThan(0);
		assertThat(saveRole).isNotNull();
		
	}
	
	@Test
	public void testListRoles() {
		List<Role> role = (List<Role>) repo.findAll();
		role.forEach(System.out :: println);
		
	}
}
