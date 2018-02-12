package com.example.manikandan.wipro.dataRepository;

import com.example.manikandan.wipro.network.model.ArticleAPIResponse;

import io.reactivex.Single;


public interface IAPIRepository {

    Single<ArticleAPIResponse> getArticleResponse();

}
