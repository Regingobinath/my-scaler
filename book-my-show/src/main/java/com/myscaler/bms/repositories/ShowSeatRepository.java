package com.myscaler.bms.repositories;


import com.myscaler.bms.models.Show;
import com.myscaler.bms.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {


}
