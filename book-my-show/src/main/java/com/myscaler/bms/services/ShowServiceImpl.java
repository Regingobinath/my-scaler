package com.myscaler.bms.services;

import com.myscaler.bms.exeptions.*;
import com.myscaler.bms.models.*;
import com.myscaler.bms.repositories.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService{
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;
    private final SeatsRepository seatsRepository;
    private final SeatTypeShowRepository seatTypeShowRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final UserRepository userRepository;

    @Autowired
    public ShowServiceImpl(MovieRepository movieRepository, ScreenRepository screenRepository,
                           SeatsRepository seatsRepository, SeatTypeShowRepository seatTypeShowRepository,
                           ShowRepository showRepository, ShowSeatRepository showSeatRepository,
                           UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
        this.seatsRepository = seatsRepository;
        this.seatTypeShowRepository = seatTypeShowRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Show createShow(int userId, int movieId, int screenId, Date startTime, Date endTime,
                           List<Pair<SeatType, Double>> pricingConfig, List<Feature> features) throws MovieNotFoundException, ScreenNotFoundException, FeatureNotSupportedByScreen, InvalidDateException, UserNotFoundException, UnAuthorizedAccessException {
        // 1. find user
        Optional<User> userOp = userRepository.findById((long) userId);
        if (userOp.isEmpty()) {
            throw new UserNotFoundException("User is not found");
        }
        User user = userOp.get();

        // Check for admin user
        if (user.getUserType() != UserType.ADMIN) {
            throw new UnAuthorizedAccessException("Only admin have access");
        }

        // 2. find movie
        Optional<Movie> movieOp = movieRepository.findById((long)movieId);
        if (movieOp.isEmpty()) {
            throw new MovieNotFoundException("Movie is not found");
        }
        Movie movie = movieOp.get();

        // 3. find screen
        Optional<Screen> screenOp = screenRepository.findById((long)screenId);
        if (screenOp.isEmpty()) {
            throw new ScreenNotFoundException("Screen is not found");
        }
        Screen screen = screenOp.get();
        // Initialize the lazy-loaded collection
        Hibernate.initialize(screen.getFeatures());

        // 4. Validate features
        List<Feature> screenFeatures = screen.getFeatures();
        if (screenFeatures == null || screenFeatures.isEmpty()) {
            throw new FeatureNotSupportedByScreen("Screen does not support any features.");
        }
        for (Feature feature : features) {
            if (!screenFeatures.contains(feature)) {
                throw new FeatureNotSupportedByScreen("Screen does not support the feature: " + feature);
            }
        }

        // 5. Validate time
        if (startTime.after(endTime) || startTime.before(new Date(System.currentTimeMillis()))) {
            throw new InvalidDateException("Start time should be before end time");
        }

        // 6. Create and save the show
        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        show.setFeatures(features);
        show = showRepository.save(show);

        // 7. Create and save SeatTypeShow
        for (Pair<SeatType, Double> pair : pricingConfig) {
            SeatTypeShow seatTypeShow = new SeatTypeShow();
            seatTypeShow.setShow(show);
            seatTypeShow.setSeatType(pair.getFirst());
            seatTypeShow.setPrice(pair.getSecond());
            seatTypeShowRepository.save(seatTypeShow);
        }

        // 8. Create and save ShowSeat
        List<Seat> seats = seatsRepository.findAll().stream()
                .filter(seat -> seat.getScreen().getId() == screenId)
                .toList();

        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(show);
            showSeat.setSeat(seat);
            showSeat.setStatus(SeatStatus.AVAILABLE);
            showSeatRepository.save(showSeat);
        }

        return show;
    }
}
