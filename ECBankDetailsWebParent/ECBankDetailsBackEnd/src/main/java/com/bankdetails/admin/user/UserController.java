package com.bankdetails.admin.user;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bankdetails.admin.district.DistrictService;
import com.bankdetails.admin.user.login.LoginUserDetails;
import com.bankdetails.entity.District;
import com.bankdetails.entity.Role;
import com.bankdetails.entity.User;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
public class UserController {
	private String defaultRedirectURL = "redirect:/users/page/1";
	private UserServiceImpl userService;
	private DistrictService districtService;
	public UserController(UserServiceImpl userService, DistrictService districtService) {
		this.userService = userService;
		this.districtService = districtService;
	}
	
//	@GetMapping
//	public String listUser(Model model, Authentication authentication) {
//		if(authentication != null && authentication.getAuthorities().stream().anyMatch(
//				auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
//		List<User> listUsers = userService.listUsers();
//		model.addAttribute("users", listUsers);
//		model.addAttribute("pageTitle", "Field Officials");
//		return "users/index";
//		}else {
//			// Redirect to the district-specific page for normal admin user
//            return "redirect:/users/userDistrict/{defaultDistrictCode}";
//		}
//	}
	
	@GetMapping
	public String listByFirstPage(Model model, Authentication authentication) {
		if(authentication != null && authentication.getAuthorities().stream().anyMatch(
				auth -> auth.getAuthority().equals("Admin"))) {

//		return listByPage(1, model);
			return defaultRedirectURL;
		}else {
			LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
			String defaultDistrictCode = userDetails.getUserDistrict().getCode();
			System.out.println("default district code: " + defaultDistrictCode );
			// Redirect to the district-specific page for normal admin user
            return "redirect:/users/userDistrict/defaultDistrictCode";
		}
	}
	
	//handler method  that list users by page
	@GetMapping("/page/{pageNum}")
	public String listByPage(@PathVariable("pageNum") Integer pageNum, Model model) {
		
		Page<User> page = userService.listByPage(pageNum);
		
		//get the content of the page
		List<User> listUsers = page.getContent();
		
		//count pages
		long startCount = (pageNum -1) * userService.USERS_PER_PAGE + 1;
		long endCount = startCount + userService.USERS_PER_PAGE - 1;
		
		//get the last number of the page
		if(endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("pageTitle", "Field Officials List");
		model.addAttribute("users", listUsers);
		
		return "users/index";
	}
	
//	@GetMapping("/{districtId}")
//	public String listUsersByDistrictId(@PathVariable("districtId") Integer districtId, Model model) {
//
//		List<User> listByDistrictId = userService.listUsersByDistrictId(districtId);
//		model.addAttribute("listByDistrictId", listByDistrictId);
//		
//		return "users/index_list_user_by_district";
//	}
	
//	@GetMapping("/userDistrict")
//	public String listUsersByDistrictId( Model model, Authentication authentication, Integer id) {
//
//		User currentUser = userService.getByEmail(authentication.getName());
//		List<User> usersInDistrict = userService.listUsersByDistrictId(currentUser.getDistrict().getId());
//		System.out.println("district ID: " + usersInDistrict);
//		model.addAttribute("listByDistrictId", usersInDistrict);
//		
//		return "users/index_list_user_by_district";
//	}
	
	@GetMapping("/userDistrict")
	public String listUsersByDistrictId(Authentication authentication, Model model) {

		LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
		District userDistrict =  userDetails.getUserDistrict();
		System.out.println("user district id: " + userDetails);
		// Check if userDistrict is not null before accessing its properties
	    if (userDistrict != null) {
	        List<User> usersInDistrict = userService.listUsersByDistrictId(userDistrict.getId());
	        System.out.println("district ID: " + userDistrict);
	        model.addAttribute("listByDistrictId", usersInDistrict);
	        return "users/index_list_user_by_district";
	    } else {
	        // Handle the case when userDistrict is null (e.g., redirect to an error page)
	        return "redirect:/error";
	    }
	}
	
	@GetMapping("/userDistrict/{districtCode}")
	public String listUsersByDistrictCode(Authentication authentication, Model model,
			@PathVariable("districtCode") String districtId) {

		LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
		District userDistrict =  userDetails.getUserDistrict();
		System.out.println("user district id: " + userDetails);
		// Check if userDistrict is not null before accessing its properties
	    if (userDistrict != null) {
	        List<User> usersInDistrict = userService.listUsersByDistrictId(userDistrict.getId());
	        System.out.println("district ID: " + userDistrict);
	        model.addAttribute("listByDistrictId", usersInDistrict);
	        return "users/index_list_user_by_district";
	    } else {
	        // Handle the case when userDistrict is null (e.g., redirect to an error page)
	        return "redirect:/error";
	    }
	}

	
	@GetMapping("/new")
	public String userForm(Model model) {
		//list user roles
		List<Role> listRoles = userService.listRoles();
		List<District> listDistricts = districtService.listDistricts();
		
		User userForm = new User();
		userForm.setEnabled(false);
		model.addAttribute("userForm", userForm);
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("listDistricts",listDistricts);
		model.addAttribute("pageTitle", "User Form");
		return "users/user_form";
	}
	
	@PostMapping("/save")
	public String addUser(User user, RedirectAttributes ra, Authentication authentication) {
		
		userService.addUser(user);
		ra.addFlashAttribute("message", "User saved successfully!");
		//return "redirect:/users";
		
//		if(authentication != null && authentication.getAuthorities().stream().anyMatch(
//				auth -> auth.getAuthority().equals("Admin"))) {
//
//			return "redirect:/users";
//		}else {
//			// Redirect to the district-specific page for normal admin user
//            return "redirect:/users/userDistrict";
//		}
		return userPageRedirect(authentication);
	}
	
	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		try {
			User user = userService.getUserId(id);
			List<Role> listRoles = userService.listRoles();
			List<District> listDistricts = districtService.listDistricts();
			model.addAttribute("userForm", user);
			model.addAttribute("listRoles", listRoles);
			model.addAttribute("listDistricts", listDistricts);
			return "users/user_form";
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/users";
	}
	
	//handler method that update user status
	@GetMapping("/{id}/enabled/{status}")
	public String updateUserEnabledStatus(
			@PathVariable("id") Integer id, 
			@PathVariable("status") boolean enabled,
			RedirectAttributes ra, Authentication authentication) {
		userService.updateUserStatus(id, enabled);
		
		//condition to enabled and disabled status
		String status = enabled ? "enabled" : "disabled";
		
		String message = "This user ID " + id + " has been " + status;
		ra.addFlashAttribute("message", message);
		
		//return "redirect:/users";
		
//		if(authentication != null && authentication.getAuthorities().stream().anyMatch(
//				auth -> auth.getAuthority().equals("Admin"))) {
//
//			return "redirect:/users";
//		}else {
//			// Redirect to the district-specific page for normal admin user
//            return "redirect:/users/userDistrict";
//		}
		
		  return userPageRedirect(authentication);
	}
	
	
	//handler method that delete user
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra, Authentication authentication) {
		try {
			userService.deleteUser(id);
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
		}
		
		
		if(authentication != null && authentication.getAuthorities().stream().anyMatch(
				auth -> auth.getAuthority().equals("Admin"))) {

			return "redirect:/users";
		}else {
			// Redirect to the district-specific page for normal admin user
            return "redirect:/users/userDistrict";
		}
		
		//return userPageRedirect(authentication);
	}
	
	//function that redirect user to the right page
	private String userPageRedirect(Authentication authentication) {
		if(authentication != null && authentication.getAuthorities().stream().anyMatch(
				auth -> auth.getAuthority().equals("Admin"))) {

			return "redirect:/users";
		}else {
			// Redirect to the district-specific page for normal admin user
            return "redirect:/users/userDistrict";
		}
	}
	
	
	
	//handler method that export data to excel
		@GetMapping("/export/excel")
		public void exportToExcel(HttpServletResponse response) throws IOException {
			List<User> listUsers = userService.listUsers();
			
			UserExcelExporter exporter = new UserExcelExporter();
			
			exporter.export(listUsers, response);
			
		}

}
