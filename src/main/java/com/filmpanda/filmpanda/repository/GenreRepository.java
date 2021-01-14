package com.filmpanda.filmpanda.repository;

import com.filmpanda.filmpanda.domain.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {

    List<Genre> findAllByOrderByNameAsc();

    Optional<Genre> findByName(String name);
}
