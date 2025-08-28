package com.myscaler.bms.services;

import com.myscaler.bms.exeptions.MovieNotFoundException;
import com.myscaler.bms.exeptions.UserNotFoundException;
import com.myscaler.bms.models.Movie;
import com.myscaler.bms.models.Rating;
import com.myscaler.bms.models.User;
import com.myscaler.bms.repositories.MovieRepository;
import com.myscaler.bms.repositories.RatingRepository;
import com.myscaler.bms.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingsServiceImpl implements RatingsService{

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    public RatingsServiceImpl(UserRepository userRepository, MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }
    @Override
    public Rating rateMovie(int userId, int movieId, int rating) throws UserNotFoundException, MovieNotFoundException {
        Optional<User> user = this.userRepository.findById((long)userId);
        user.orElseThrow(()-> new UserNotFoundException("User not found exception"));

        Optional<Movie> movie = this.movieRepository.findById((long)movieId);
        movie.orElseThrow(()->new MovieNotFoundException("Movie Not found"));

        Optional<Rating> ratingOpt = this.ratingRepository.findByUserIdAndMovieId(userId, movieId);
        Rating newRating = ratingOpt.orElse(new Rating());

        newRating.setUser(user.get());
        newRating.setMovie(movie.get());
        newRating.setRating(rating);

        return this.ratingRepository.save(newRating);
    }

    @Override
    public double getAverageRating(int movieId) throws MovieNotFoundException {
        Optional<Movie> movie = this.movieRepository.findById((long)movieId);
        movie.orElseThrow(()->new MovieNotFoundException("Movie Not found"));
        return this.ratingRepository.getAverageRatingByMovieId(movieId);
    }
}
