package com.example.demo.Repository;

import java.awt.desktop.AppEvent;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Appoinment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appoinment, Long>{
List<Appoinment> findByEmail(String email);
}
