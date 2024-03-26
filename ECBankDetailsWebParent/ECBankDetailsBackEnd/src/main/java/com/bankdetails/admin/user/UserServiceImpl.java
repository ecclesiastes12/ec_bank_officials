package com.bankdetails.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankdetails.admin.district.DistrictRespository;
import com.bankdetails.entity.District;
import com.bankdetails.entity.Role;
import com.bankdetails.entity.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	//constant variable for page size thus total number of list per page
		public static final int USERS_PER_PAGE = 2;

	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private PasswordEncoder passwordEncoder;
	private DistrictRespository districtRespo;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo, 
			PasswordEncoder passwordEncoder,
			RoleRepository roleRepo,
			DistrictRespository districtRespo) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.roleRepo = roleRepo;
		this.districtRespo = districtRespo;
	}
	
	@Override
	public List<User> listUsers() {
		
		return userRepo.findAll();
	}

	@Override
	public List<User> listUsersByDistrictId(Integer districtId) {// TODO Auto-generated method stub
		return userRepo.findByDistrictId(districtId);
	}

	//code be optimistic locking
//	@Override
//	public User addUser(User user) {
//		//check if the form is in the edit mode or new mode before saving the data
//		boolean isUserUpdatingForm = user.getId() != null;
//		
//		if(isUserUpdatingForm) {
//			//retrieves user details from the database
//			User existingUser = userRepo.findById(user.getId()).get();
//			//checks if password is empty
//			//NB if the password is empty it means user want to maintain the old password
//			if(user.getPassword().isEmpty()) {
//				//reads password from the database and set it to user object
//				user.setPassword(existingUser.getPassword());
//			}else {
//				//encode the new password if the password field is not empty
//				encodePassword(user);
//			}
//		}else {
//			encodePassword(user);//encode password before saving it
//		}
//		
//		return userRepo.save(user);
//	}
	
	
	
	@Override
	public User addUser(User user) {
		//check if the form is in the edit mode or new mode before saving the data
		boolean isUserUpdatingForm = user.getId() != null;
		
		if(isUserUpdatingForm) {
			//retrieves user details from the database
			User existingUser = userRepo.findById(user.getId()).get();
			if(!existingUser.getVersion().equals(user.getVersion())) {
				throw new OptimisticLockingFailureException("This user is being updated by another person");
			}else {
				//checks if password is empty
				//NB if the password is empty it means user want to maintain the old password
				if(user.getPassword().isEmpty()) {
					//reads password from the database and set it to user object
					user.setPassword(existingUser.getPassword());
				}else {
					//encode the new password if the password field is not empty
					encodePassword(user);
				}
			}
		}else {
			encodePassword(user);//encode password before saving it
		}
		
		return userRepo.save(user);
	}

//	@Override
//	public User createUserTable4District(User user) {
//		// TODO Auto-generated method stub
//		return userRepo.save(user);
//	}

	@Override
	public User getByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}

	@Override
	public User getUserId(Integer id) throws UserNotFoundException {
		try {
			return userRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new UserNotFoundException("Could not find user ID: " + id);
		}
	}

	@Override
	public void deleteUser(Integer id) throws UserNotFoundException {
		Long countById = userRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new UserNotFoundException("Could not find user ID: " + id);
		}
		
		userRepo.deleteById(id);
	}

	//method that update user status
	@Override
	public void updateUserStatus(Integer id, boolean enabled) {
		userRepo.updateStatus(id, enabled);
		
	}

	@Override
	public void encodePassword(User user) {
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
	}

	@Override
	public boolean isEmailUnique(Integer id, String email) {
		//retrieve user email from the database
		User userByEmail = userRepo.getUserByEmail(email);
		
		//check if user email exist in the db
		if(userByEmail == null)
			return true;// if it returns null meaning email is unique in the db
		
		//check if the form is in the new or edit mode
		// if id is null meaning the form is in the new mode
		// if id is not null meaning the form is in the edit mode
		boolean isCreatingNew = (id == null);
		
		if(isCreatingNew) {
			// if email is not null meaning the email exist in the db
			// therefore the email is not unique. New email must be provided
			if(userByEmail != null) {
				return false;
			}
		}else {
			// if the ID of the user find by email is different from the ID
			// of the user being edited then email is not unique
			if(userByEmail.getId() != id) {
				return false;
			}
			
		}
		
		
		return true;
	}

	@Override
	public List<Role> listRoles() {
		return (List<Role>) roleRepo.findAll();
	}

	//business method that list users by page
	@Override
	public Page<User> listByPage(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1 , USERS_PER_PAGE);
		return userRepo.findAll(pageable);
	}

	@Override
	public int countUsersByDistrictId(int districtId) {
        return userRepo.countByDistrictId(districtId);
    }
	
	
	
}
