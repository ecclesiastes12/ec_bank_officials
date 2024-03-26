package com.bankdetails.admin.user;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bankdetails.entity.District;
import com.bankdetails.entity.Role;
import com.bankdetails.entity.User;

public interface UserService {

	public List<User> listUsers();
	public List<User> listUsersByDistrictId(Integer districtId);
	public User addUser(User user);
	public User getByEmail(String email);
	public void encodePassword(User user);
	public User getUserId(Integer id) throws UserNotFoundException;
	public void deleteUser(Integer id) throws UserNotFoundException;
	public void updateUserStatus(Integer id, boolean enabled);
	public boolean isEmailUnique(Integer id, String email);
	public List<Role> listRoles();
	public int countUsersByDistrictId(int districtId);
	//methtod that list users by page
	public Page<User> listByPage(int pageNum);
}
