package com.example.demo.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Appoinment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String email;
    private String name;
    private Integer doctorId;
    private LocalDate date;
    private LocalTime time;
    private String note;
   
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email=email;
    	
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Appoinment(Long id, String name, Integer doctorId, LocalDate date, LocalTime time, String note,String email) {
		super();
		this.id = id;
		this.name = name;
		this.doctorId = doctorId;
		this.date = date;
		this.time = time;
		this.note = note;
		this.email=email;
	}
	public Appoinment() {
		super();
		// TODO Auto-generated constructor stub
	}
    

}
