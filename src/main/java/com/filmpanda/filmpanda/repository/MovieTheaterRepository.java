package com.filmpanda.filmpanda.repository;

import com.filmpanda.filmpanda.domain.entities.MovieTheater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieTheaterRepository extends JpaRepository<MovieTheater, String> {

    Optional<MovieTheater> findByName(String name);

    List<MovieTheater> findAllByOrderByNameAsc();
}
