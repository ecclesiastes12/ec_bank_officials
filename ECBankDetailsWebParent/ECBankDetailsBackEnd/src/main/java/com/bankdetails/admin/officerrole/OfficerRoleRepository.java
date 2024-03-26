package com.bankdetails.admin.officerrole;

import org.springframework.data.repository.CrudRepository;

import com.bankdetails.entity.OfficerRole;

public interface OfficerRoleRepository extends CrudRepository<OfficerRole, Integer>{

	Long countById(Integer id);

}
