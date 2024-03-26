package com.bankdetails.site.district.officers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bankdetails.entity.District;
import com.bankdetails.entity.Officer;
import com.bankdetails.entity.OfficerRole;
import com.bankdetails.site.district.DistrictService;
import com.bankdetails.site.district.officersrole.OfficerRoleService;

@Controller
@RequestMapping("/officers")
public class OfficerController {
	
	@Autowired private DistrictService districtService;
	@Autowired private OfficerRoleService officerRoleService;
	@Autowired private OfficerService officerService;
	
	
	@GetMapping("/new")
	public String viewOfficerForm(Model model) {
		
		List<District> listDistricts = districtService.listDistrict();
		List<OfficerRole> listOfficerRoles = officerRoleService.listOfficerRoles();
		
		Officer officer = new Officer();
		
		model.addAttribute("officerForm", officer);
		model.addAttribute("listDistricts", listDistricts);
		model.addAttribute("listOfficerRoles", listOfficerRoles);
		
		return "officer/officer_form";
	}
	
	@PostMapping("/save")
	public String addOfficer(Officer officer) {
		officerService.addOfficer(officer);
		
		
		return "officer/success";
	}

}
