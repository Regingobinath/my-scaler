package com.myscaler.bms.repositories;


import com.myscaler.bms.models.Seat;
import com.myscaler.bms.models.SeatTypeShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeShowRepository extends JpaRepository<SeatTypeShow, Long> {

}
