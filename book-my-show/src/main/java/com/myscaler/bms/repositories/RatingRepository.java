package com.myscaler.bms.repositories;

import com.myscaler.bms.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndMovieId(long userId, long movieId);
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.movie.id = :movieId")
    double getAverageRatingByMovieId(@Param("movieId") long movieId);
}
