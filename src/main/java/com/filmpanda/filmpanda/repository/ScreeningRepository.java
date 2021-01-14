package com.filmpanda.filmpanda.repository;

import com.filmpanda.filmpanda.domain.entities.Movie;
import com.filmpanda.filmpanda.domain.entities.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, String> {

    List<Screening> findAllByMovie(Movie movie);
}
