package com.example.demo.Controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Entity.DoctorEntity;
import com.example.demo.Repository.Doctorrepository;
import com.example.demo.Service.DoctorService;


@RestController
@RequestMapping("/api/doctor")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {
	@Autowired
	private Doctorrepository repo;
	@Autowired
	private DoctorService service;
	
	@GetMapping
	public List<DoctorEntity> getAllDoctor(){
		return repo.findAll();
	}
	@PostMapping("/addDoctor")
	public Object adddoctor(@RequestBody DoctorEntity doctor) {
		DoctorEntity u=service.doctoradd(doctor);
		if(u!=null) 
			return ResponseEntity.ok(u);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","email Already exist"));
		
	}
	@PostMapping("/doctorLogin")
	public Object doctorLogin(@RequestBody DoctorEntity doctor) {
		
		DoctorEntity u=service.Doctorlogin(doctor.getEmail(),doctor.getPassword());
		if(u!=null) {
			return u;
		}
		else {
			return "invalid";
		}
	}
	

}
