package com.capstone.petcare2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.petcare2.Models.BookingModel;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, Long> {
    
}

