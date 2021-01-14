package com.filmpanda.filmpanda.domain.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    private String id;
    private String title;
    private User reviewer;
    private Double rating;
    private String description;
    private Movie movie;

    public Review() {
    }

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(
            name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "reviewer_id", referencedColumnName = "id")
    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    @Column(name = "rating", nullable = false)
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
