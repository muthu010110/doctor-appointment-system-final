package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.DoctorEntity;
import com.example.demo.Repository.Doctorrepository;

@Service
public class DoctorService {
	@Autowired
	private Doctorrepository repo;
	
	public DoctorEntity Doctorlogin(String email,String password) {
		return repo.findByEmailAndPassword(email, password);
		
	}
	
	public DoctorEntity doctoradd(DoctorEntity doctor) {
		DoctorEntity existingDoctor=repo.findByEmail(doctor.getEmail());
		if(existingDoctor!=null)
		 {
			return null;
		}
		return repo.save(doctor);
	}

}
