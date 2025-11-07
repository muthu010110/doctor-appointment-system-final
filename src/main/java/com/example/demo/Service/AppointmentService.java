package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Appoinment;
import com.example.demo.Repository.AppointmentRepository;
import java.util.*;
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmailService emailService;

    public Appoinment saveAppointment(Appoinment appointment) {
   
        Appoinment saved = appointmentRepository.save(appointment);
        emailService.sendAppointmentEmail(saved.getEmail(), saved);
        return saved;
    }
    public List<Appoinment> getAppoinmentsByDoctorEmail(String email){
    	return appointmentRepository.findByEmail(email);
    }
}
