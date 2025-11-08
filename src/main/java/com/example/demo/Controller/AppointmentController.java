
package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Appoinment;
import com.example.demo.Service.AppointmentService;

import java.util.List;
import java.util.Map;
//AppointmentController.java
@RestController
@RequestMapping("/api/appointment")
@CrossOrigin(origins = {"http://localhost:3000", "https://doctor-appoinment-system-frontend.onrender.com"})
public class AppointmentController {

 @Autowired private AppointmentService appointmentService;

 @PostMapping
 public ResponseEntity<?> bookAppointment(@RequestBody Appoinment appoinment){
     try {
         Appoinment saved = appointmentService.saveAppointment(appoinment);
         return ResponseEntity.status(201).body(saved);
     } catch (IllegalArgumentException e) { // our own validations
         return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
     } catch (org.springframework.http.converter.HttpMessageNotReadableException e) {
         // e.g., date/time parse issues
         return ResponseEntity.badRequest().body(Map.of("message", "Invalid date/time format"));
     } catch (org.springframework.dao.DataIntegrityViolationException e) {
         return ResponseEntity.badRequest().body(Map.of("message", "Invalid data or DB constraint"));
     } catch (Exception e) {
         e.printStackTrace(); // view exact cause in Render logs
         return ResponseEntity.status(500).body(Map.of("message", "Unexpected error"));
     }
 }

 @GetMapping("/doctor/{email}")
 public ResponseEntity<List<Appoinment>> getAppointmentByDoctor(@PathVariable String email){
     return ResponseEntity.ok(appointmentService.getAppoinmentsByDoctorEmail(email));
 }
}
