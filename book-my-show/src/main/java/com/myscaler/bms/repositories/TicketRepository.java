package com.myscaler.bms.repositories;


import com.myscaler.bms.models.Ticket;
import com.myscaler.bms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findById(long id);
}
