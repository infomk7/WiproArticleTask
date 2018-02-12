package com.example.manikandan.wipro.dataRepository;

import com.example.manikandan.wipro.network.APIService;
import com.example.manikandan.wipro.network.model.ArticleAPIResponse;

import io.reactivex.Single;

/*API Service Data Wrapper Class*/
public class APIRepository implements IAPIRepository {

    private APIService mApiService;

    public APIRepository(){
        mApiService =APIService.Creator.getApiService();

    }
    @Override
    public Single<ArticleAPIResponse> getArticleResponse() {
        return mApiService.getResponse();
    }
}
