package com.bankdetails.admin.district;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bankdetails.entity.District;

@Controller
@RequestMapping("/districts")
public class DistrictController {

	@Autowired
	private DistrictService distService;
	
	
	@GetMapping
	public String listDistricts(Model model) {
		List<District> listDistricts = distService.listDistricts();
		model.addAttribute("listDistricts", listDistricts);
		model.addAttribute("pageTitle", "District List");
		return "districts/index";
	}
	
	@GetMapping("/new")
	public String viewDistrictForm(Model model) {
		District district = new District();
		model.addAttribute("districtForm", district);
		model.addAttribute("pageTitle", "District Form");
		return "districts/district_form";
	}
	
	@PostMapping("/save")
	public String addDistrict(District district, RedirectAttributes ra) {
		
		distService.addDistrict(district);
		ra.addFlashAttribute("message", "District saved successfully!");
		
		return "redirect:/districts";
	}
	
//	@GetMapping("/new")
//    public String showCreateDistrictForm(Model model) {
//        // Populate the model with necessary data for the form
//        model.addAttribute("district", new District1());
//        return "districts/new_district_form";
//    }
//
//    @PostMapping("/save")
//    public String createDistrict(@ModelAttribute District1 district) {
//      //  distService.createDistrictWithUserAndBankDetails(district);
//    	districtRepo.save(district);
//        return "redirect:/districts";
//    }
	
	@GetMapping("/edit/{id}")
	public String updateDistrict(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		
		try {
			District district = distService.getDistrictId(id);
			model.addAttribute("districtForm", district);
		} catch (DistrictNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
		}
		
		return "districts/district_form";	
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDistrict(@PathVariable("id") Integer id, RedirectAttributes ra) {
		try {
			distService.deleteDistrict(id);
		} catch (DistrictNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/districts";
	}
}
