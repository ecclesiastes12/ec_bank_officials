package com.bankdetails.site.district;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankdetails.entity.District;

@Service
public class DistrictService {

	@Autowired private DistrictRepository districtRepo;
	
	public List<District> listDistrict() {
		return districtRepo.findAll();
	}

	public District getDistrictId(Integer id) throws DistrictNotFoundException {
		
		try {
			return districtRepo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new DistrictNotFoundException("Could not find user ID: " + id);
		
		}
	}
}
