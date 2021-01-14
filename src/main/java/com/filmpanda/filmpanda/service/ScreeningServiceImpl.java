package com.filmpanda.filmpanda.service;

import com.filmpanda.filmpanda.domain.entities.Movie;
import com.filmpanda.filmpanda.domain.entities.Screening;
import com.filmpanda.filmpanda.domain.models.service.ScreeningServiceModel;
import com.filmpanda.filmpanda.repository.ScreeningRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    @Autowired
    public ScreeningServiceImpl(ScreeningRepository screeningRepository, MovieService movieService, ModelMapper modelMapper) {
        this.screeningRepository = screeningRepository;
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    /**
     * Finds all screenings of a movie.
     * @param movieId is the id of the movie.
     * @return a list of screening service models.
     */
    @Override
    public List<ScreeningServiceModel> findAllScreeningsByMovieId(String movieId) {
        Movie movie = this.modelMapper.map(this.movieService.findMovieById(movieId), Movie.class);
        return this.screeningRepository.findAllByMovie(movie)
                .stream()
                .map(s -> this.modelMapper.map(s, ScreeningServiceModel.class))
                .collect(Collectors.toList());
    }

    /**
     * Attempts to add a screening.
     * @param screeningServiceModel transfers data about the screening to the method.
     * @return a respective model of the screening.
     */
    @Override
    public ScreeningServiceModel addScreening(ScreeningServiceModel screeningServiceModel) {
        Screening screening = this.modelMapper.map(screeningServiceModel, Screening.class);
        return this.modelMapper.map(this.screeningRepository.saveAndFlush(screening), ScreeningServiceModel.class);
    }

    /**
     * Attempts to delete a screening.
     * @param screeningId is the id of the screening which we are deleting.
     * @return a respective model of the screening.
     */
    @Override
    public ScreeningServiceModel deleteScreening(String screeningId) {
        Screening screening = this.screeningRepository.findById(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found!"));
        this.screeningRepository.delete(screening);
        return this.modelMapper.map(screening, ScreeningServiceModel.class);
    }
}
