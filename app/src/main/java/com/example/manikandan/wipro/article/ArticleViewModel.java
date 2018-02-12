package com.example.manikandan.wipro.article;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.manikandan.wipro.network.model.ArticleAPIResponse;
import com.example.manikandan.wipro.dataRepository.APIRepository;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/*Article Activity View Model*/
public class ArticleViewModel extends ViewModel {

    private MediatorLiveData<ArticleAPIResponse> articleDataResponse;
    private MediatorLiveData<Boolean> progressLoading;
    private APIRepository apiRepository;

    public ArticleViewModel() {
        apiRepository = new APIRepository();
        articleDataResponse = new MediatorLiveData<>();
        progressLoading = new MediatorLiveData<>();
    }

    /*Consume Article API Service*/
    public void callArticleAPIService() {
        try {
            progressLoading.setValue(true);
            Single<ArticleAPIResponse> response = apiRepository.getArticleResponse();
            response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<ArticleAPIResponse>() {

                        @Override
                        public void onSuccess(ArticleAPIResponse apiResponse) {
                            progressLoading.setValue(false);
                            if (apiResponse != null)
                                articleDataResponse.setValue(apiResponse);
                        }

                        @Override
                        public void onError(Throwable e) {
                            progressLoading.setValue(false);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*Get Article Live data*/
    public LiveData<ArticleAPIResponse> getArticleResponse() {
        if (articleDataResponse == null) {
            articleDataResponse = new MediatorLiveData<>();
        }
        return articleDataResponse;
    }

    /*Show Progress Bar*/
    public LiveData<Boolean> getProgressLoading() {
        if (progressLoading == null) {
            progressLoading = new MediatorLiveData<>();
        }
        return progressLoading;
    }
}
