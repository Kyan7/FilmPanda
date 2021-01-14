package com.filmpanda.filmpanda.domain.models.service;

public class ReviewServiceModel {

    private String id;
    private String title;
    private UserServiceModel reviewer;
    private Double rating;
    private String description;
    private MovieServiceModel movie;

    public ReviewServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserServiceModel getReviewer() {
        return reviewer;
    }

    public void setReviewer(UserServiceModel reviewer) {
        this.reviewer = reviewer;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MovieServiceModel getMovie() {
        return movie;
    }

    public void setMovie(MovieServiceModel movie) {
        this.movie = movie;
    }
}
