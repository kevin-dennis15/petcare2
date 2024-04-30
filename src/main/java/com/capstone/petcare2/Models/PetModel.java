package com.capstone.petcare2.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Pets")
public class PetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;
    private String breed;
    private int age;
    private String notes;
    private String ownerEmail;
	public Object getOwnerEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getBreed() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setBreed(Object breed2) {
		// TODO Auto-generated method stub
		
	}
	public Object getSpecies() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setSpecies(Object species2) {
		// TODO Auto-generated method stub
		
	}
	public int getAge() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setAge(int age2) {
		// TODO Auto-generated method stub
		
	}
	public void setOwnerEmail(Object ownerEmail2) {
		// TODO Auto-generated method stub
		
	}
	public Object getNotes() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setNotes(Object notes2) {
		// TODO Auto-generated method stub
		
	}
}
