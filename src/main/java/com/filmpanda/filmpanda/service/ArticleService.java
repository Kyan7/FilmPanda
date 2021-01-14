package com.filmpanda.filmpanda.service;

import com.filmpanda.filmpanda.domain.models.service.ArticleServiceModel;

import java.util.List;

public interface ArticleService {

    List<ArticleServiceModel> findAllArticles();

    ArticleServiceModel addArticle(ArticleServiceModel articleServiceModel);

    ArticleServiceModel findArticleByIdAndIncrementViews(String id);

    ArticleServiceModel findArticleById(String id);

    ArticleServiceModel editArticle(String id, ArticleServiceModel articleServiceModel, boolean isAssociatedMoviesEdited);

    ArticleServiceModel editArticleWithEditedAssociatedMovies(String id, ArticleServiceModel articleServiceModel);

    ArticleServiceModel editArticleWithUneditedAssociatedMovies(String id, ArticleServiceModel articleServiceModel);

    ArticleServiceModel deleteArticle(String id);
}
