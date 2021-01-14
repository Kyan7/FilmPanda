package com.filmpanda.filmpanda.repository;

import com.filmpanda.filmpanda.domain.entities.Movie;
import com.filmpanda.filmpanda.domain.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    List<Review> findAllByMovie(Movie movie);
}
