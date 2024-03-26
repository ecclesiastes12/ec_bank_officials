
package com.bankdetails.admin.district;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankdetails.entity.District;

@Service
public class DistrictService {

	@Autowired private DistrictRespository distRepo;
	
	public List<District> listDistricts(){
		return distRepo.findAll();
	}
	
	public District getDistrictId(Integer id) throws DistrictNotFoundException {
		try {
			return distRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new DistrictNotFoundException("Could not find District ID: " + id);
		}
	}
	
	public District addDistrict(District district) {
		return distRepo.save(district);
	}
	
	public void deleteDistrict(Integer id) throws DistrictNotFoundException {
		Long countById = distRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new DistrictNotFoundException("Could not find District ID: " + id);
		}
		
		distRepo.deleteById(id);
	}
}
