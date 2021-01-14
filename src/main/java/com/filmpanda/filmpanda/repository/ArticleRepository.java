package com.filmpanda.filmpanda.repository;

import com.filmpanda.filmpanda.domain.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    Optional<Article> findByTitle(String title);
}
