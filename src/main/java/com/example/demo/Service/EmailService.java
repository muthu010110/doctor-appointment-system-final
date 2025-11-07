package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Appoinment;
import com.example.demo.Entity.DoctorEntity;
import com.example.demo.Repository.Doctorrepository;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Doctorrepository doctorRepository;

    public void sendAppointmentEmail(String toEmail, Appoinment appointment) {
    	 DoctorEntity doctor = doctorRepository.findById(appointment.getDoctorId())
                 .orElseThrow(() -> new RuntimeException("Doctor not found"));
    	 
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Appointment Confirmation");
        message.setText(
                "Hello " + appointment.getName() + ",\n\n" +
                "Your appointment with Dr. " + doctor.getName() + " (" + doctor.getSpecialization() + ") is confirmed.\n" +
                "Date: " + appointment.getDate() + "\n" +
                "Time: " + appointment.getTime() + "\n" +
                "Notes: " + appointment.getNote() + "\n\n" +
                "Thank you!"
            );

        mailSender.send(message);
    }
}
