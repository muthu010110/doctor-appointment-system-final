package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.DoctorEntity;
@Repository
public interface Doctorrepository extends JpaRepository<DoctorEntity,Integer> {
DoctorEntity findByName(String name);
DoctorEntity findByEmail(String email);
DoctorEntity findByEmailAndPassword(String email,String password);

}
