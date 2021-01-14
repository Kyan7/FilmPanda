package com.filmpanda.filmpanda.repository;

import com.filmpanda.filmpanda.domain.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    List<Movie> findAllByOrderByTitle();

    Optional<Movie> findByTitle(String title);
}
