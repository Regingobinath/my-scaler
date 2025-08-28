package com.myscaler.bms.services;

import com.myscaler.bms.exeptions.MovieNotFoundException;
import com.myscaler.bms.exeptions.UserNotFoundException;
import com.myscaler.bms.models.Rating;
import com.myscaler.bms.repositories.RatingRepository;
import com.myscaler.bms.repositories.UserRepository;

public interface RatingsService {

    public Rating rateMovie(int userId, int movieId, int rating) throws UserNotFoundException, MovieNotFoundException;

    public double getAverageRating(int movieId) throws MovieNotFoundException;
}
