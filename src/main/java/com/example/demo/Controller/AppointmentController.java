
package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Appoinment;
import com.example.demo.Service.AppointmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointment")
@CrossOrigin(origins = {"http://localhost:3000", "https://doctor-appoinment-system-frontend.onrender.com"}) 
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

   
    @PostMapping
    public ResponseEntity<Appoinment> bookAppointment(@RequestBody Appoinment appoinment){ 

        Appoinment savedAppointment = appointmentService.saveAppointment(appoinment);
        return ResponseEntity.status(201).body(savedAppointment);
    }
    @GetMapping("/doctor/{email}")
    public List<Appoinment> getAppointmentByDoctor(@PathVariable String email){
    	return appointmentService.getAppoinmentsByDoctorEmail(email);
    }
    
    

}
