package com.developer.reservation.repository;

import org.springframework.data.repository.CrudRepository;

import com.developer.reservation.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
