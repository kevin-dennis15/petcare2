package com.capstone.petcare2.Models;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Data
@Entity
@Table(name = "bookings")
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ownerEmail;
    private int petId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
	public Long getPetId() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getOwnerEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setId(Long id2) {
		// TODO Auto-generated method stub
		
	}

}





