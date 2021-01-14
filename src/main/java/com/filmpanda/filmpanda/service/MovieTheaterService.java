package com.filmpanda.filmpanda.service;

import com.filmpanda.filmpanda.domain.models.service.MovieTheaterServiceModel;

import java.util.List;

public interface MovieTheaterService {

    List<MovieTheaterServiceModel> findAllMovieTheaters();

    List<MovieTheaterServiceModel> findAllMovieTheatersOrderByName();

    MovieTheaterServiceModel addMovieTheater(MovieTheaterServiceModel movieTheaterServiceModel);

    MovieTheaterServiceModel findMovieTheaterById(String id);

    MovieTheaterServiceModel editMovieTheater(String id, MovieTheaterServiceModel movieTheaterServiceModel);

    MovieTheaterServiceModel deleteMovie(String id);
}
