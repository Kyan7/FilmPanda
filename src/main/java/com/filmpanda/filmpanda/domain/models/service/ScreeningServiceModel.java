package com.filmpanda.filmpanda.domain.models.service;

public class ScreeningServiceModel {

    private String id;
    private MovieTheaterServiceModel movieTheater;
    private String type;
    private Double price;
    private String timeStamp;
    private MovieServiceModel movie;

    public ScreeningServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MovieTheaterServiceModel getMovieTheater() {
        return movieTheater;
    }

    public void setMovieTheater(MovieTheaterServiceModel movieTheater) {
        this.movieTheater = movieTheater;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public MovieServiceModel getMovie() {
        return movie;
    }

    public void setMovie(MovieServiceModel movie) {
        this.movie = movie;
    }
}
