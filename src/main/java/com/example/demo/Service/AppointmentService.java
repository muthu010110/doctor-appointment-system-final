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
     // (Optional) quick sanity: null/blank checks
     if (appointment.getEmail() == null || appointment.getEmail().isBlank())
         throw new IllegalArgumentException("Email is required");
     if (appointment.getDoctorId() == null)
         throw new IllegalArgumentException("doctorId is required");
     if (appointment.getDate() == null)
         throw new IllegalArgumentException("date is required");
     if (appointment.getTime() == null)
         throw new IllegalArgumentException("time is required");

     Appoinment saved = appointmentRepository.save(appointment);

     try {
         emailService.sendAppointmentEmail(saved.getEmail(), saved);
     } catch (Exception e) {
         // log but DO NOT fail the booking
         System.err.println("Email send failed: " + e.getMessage());
     }
     return saved;
 }

 public List<Appoinment> getAppoinmentsByDoctorEmail(String email){
     return appointmentRepository.findByEmail(email); // note: this returns by PATIENT email
 }
}

