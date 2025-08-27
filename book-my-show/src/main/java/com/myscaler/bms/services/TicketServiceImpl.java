package com.myscaler.bms.services;

import com.myscaler.bms.exeptions.SeatsNotAvailable;
import com.myscaler.bms.exeptions.UserNotFoundException;
import com.myscaler.bms.models.*;
import com.myscaler.bms.repositories.ShowSeatRepository;
import com.myscaler.bms.repositories.TicketRepository;
import com.myscaler.bms.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final UserRepository userRepository;
    private final ShowSeatRepository showSeatRepository;
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(UserRepository userRepository, ShowSeatRepository showSeatRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
    }
    @Override
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws Exception {
        Optional<User> user = this.userRepository.findById((long) userId);
        user.orElseThrow(() -> new UserNotFoundException("User not found"));

        List<ShowSeat> showSeats = this.reserveSeats(showSeatIds);

        Ticket ticket = new Ticket();
        ticket.setSeats(this.getSeats(showSeats));
        ticket.setUser(user.get());
        ticket.setTimeOfBooking(Calendar.getInstance().getTime());
        ticket.setStatus(TicketStatus.UNPAID);

        return this.ticketRepository.save(ticket);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    private List<ShowSeat> reserveSeats(List<Integer> showSeatIds) throws SeatsNotAvailable{
        List<Long> showSeatIdsLong = showSeatIds.stream().map(id->(long)id).toList();

        List<ShowSeat> showSeats = this.showSeatRepository.findAllById(showSeatIdsLong);
        if(showSeatIdsLong.size() != showSeats.size()) {
            throw new SeatsNotAvailable("Seats are not available");
        }
        List<ShowSeat> unAvailableSeats = showSeats.stream().filter(seat -> !seat.getStatus().equals(SeatStatus.AVAILABLE)).toList();
        if (!unAvailableSeats.isEmpty()) {
            throw new SeatsNotAvailable("Seats are not available");
        }

        showSeats.forEach(seat -> seat.setStatus(SeatStatus.BLOCKED));
        showSeats = this.showSeatRepository.saveAll(showSeats);

        return showSeats;
    }

    private List<Seat> getSeats(List<ShowSeat> showSeats) {
        return showSeats.stream().map(ShowSeat::getSeat).toList();
    }
}
