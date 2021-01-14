package com.filmpanda.filmpanda.service;

import com.filmpanda.filmpanda.domain.models.service.ScreeningServiceModel;

import java.util.List;

public interface ScreeningService {

    List<ScreeningServiceModel> findAllScreeningsByMovieId(String movieId);

    ScreeningServiceModel addScreening(ScreeningServiceModel screeningServiceModel);

    ScreeningServiceModel deleteScreening(String screeningId);
}
