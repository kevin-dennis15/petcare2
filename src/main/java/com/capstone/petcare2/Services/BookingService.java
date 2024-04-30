package com.capstone.petcare2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.petcare2.Models.BookingModel;
import com.capstone.petcare2.Models.PetModel;
import com.capstone.petcare2.Repository.BookingRepository;
import com.capstone.petcare2.Repository.PetRepository;

import java.util.List;
import java.util.Optional;


@Service
public class BookingService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private  BookingRepository bookingRepository;


    public List<BookingModel> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingModel getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public BookingModel createBooking(BookingModel booking) {
        Optional<PetModel> petOptional = petRepository.findById(booking.getPetId());
        if (petOptional.isPresent()) {
            PetModel pet = petOptional.get();
            if (pet.getOwnerEmail().equals(booking.getOwnerEmail())) {
                return bookingRepository.save(booking);
            }
        }
        return null; 
    }

    public BookingModel updateBooking(Long id, BookingModel updatedBooking) {
        BookingModel existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking != null) {
            updatedBooking.setId(id);
            return bookingRepository.save(updatedBooking);
        }
        return null;
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
