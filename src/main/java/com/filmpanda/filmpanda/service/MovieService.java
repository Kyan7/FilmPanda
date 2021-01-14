package com.filmpanda.filmpanda.service;

import com.filmpanda.filmpanda.domain.models.service.MovieServiceModel;

import java.util.List;

public interface MovieService {

    List<MovieServiceModel> findAllMovies();

    List<MovieServiceModel> findAllMoviesOrderByTitle();

    MovieServiceModel addMovie(MovieServiceModel movieServiceModel);

    MovieServiceModel findMovieByIdAndIncrementViews(String id);

    MovieServiceModel findMovieById(String id);

    MovieServiceModel editMovie(String id, MovieServiceModel movieServiceModel, boolean isGenresEdited);

    MovieServiceModel editMovieWithEditedGenres(String id, MovieServiceModel movieServiceModel);

    MovieServiceModel editMovieWithUneditedGenres(String id, MovieServiceModel movieServiceModel);

    MovieServiceModel deleteMovie(String id);
}
