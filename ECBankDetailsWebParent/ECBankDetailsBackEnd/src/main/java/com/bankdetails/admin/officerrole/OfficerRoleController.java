package com.bankdetails.admin.officerrole;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bankdetails.admin.user.UserNotFoundException;
import com.bankdetails.entity.District;
import com.bankdetails.entity.OfficerRole;
import com.bankdetails.entity.Role;
import com.bankdetails.entity.User;

@Controller
@RequestMapping("/positions")
public class OfficerRoleController {

	private OfficerRoleServiceImpl officerRoleService;

	public OfficerRoleController(OfficerRoleServiceImpl officerRoleService) {
		super();
		this.officerRoleService = officerRoleService;
	}
	
	@GetMapping
	public String listOfficerRole(Model model) {
		List<OfficerRole> listOfficerRoles = officerRoleService.listOfficerRoles();
		OfficerRole officerRole = new OfficerRole();
		model.addAttribute("officer_role", officerRole);
		model.addAttribute("listOfficerRoles", listOfficerRoles);
		return "positions/index";
	}
	
	
//	@GetMapping("/new")
//	public String viewOfficerRoleForm(OfficerRole officerRole,Model model) {
//		
//		officerRole = new OfficerRole();
//		model.addAttribute("officer_role", officerRole);
//		
//		return "positions/officer_role_form";
//	}
	
	@PostMapping("/save")
	public String saveOfficerRoleForm(OfficerRole officerRole) {
		
		officerRoleService.saveOfficerRole(officerRole);
		
		return "redirect:/positions";
	}
	
	@GetMapping("/edit/{id}")
	public String editOfficerRole(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) throws OfficerRoleNotFoundException {
		try {
		
			OfficerRole officerRole = officerRoleService.findOfficerRoleById(id);
			List<OfficerRole> listOfficerRoles = officerRoleService.listOfficerRoles();
			
			model.addAttribute("officer_role", officerRole);
			model.addAttribute("listOfficerRoles", listOfficerRoles);
		} catch (OfficerRoleNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
		}
		
		return "positions/index";
	}
	
}
