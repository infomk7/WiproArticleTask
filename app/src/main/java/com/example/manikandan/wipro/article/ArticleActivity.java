package com.example.manikandan.wipro.article;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.manikandan.wipro.R;
import com.example.manikandan.wipro.base.BaseActivity;
import com.example.manikandan.wipro.adapter.ArticleAdapter;

/*Display About Country Articles Activity */
public class ArticleActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ArticleViewModel articleViewModel;
    private RecyclerView articleRecyclerView;
    private ArticleAdapter articleAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        if (isNetworkAvailable()) {
            initWidget();
            loadAdapter();
        } else {
            noNetworkConnectionDialog();
        }
    }

    /*Initialize the view*/
    public void initWidget() {
        try {
            articleRecyclerView = findViewById(R.id.rv_article_view);
            articleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            articleViewModel.callArticleAPIService();
            swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
            swipeRefreshLayout.setOnRefreshListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*call api service and load data into ListView*/
    public void loadAdapter() {
        try {
            articleViewModel.getProgressLoading().observe(this, isLoading -> {
                if (isLoading != null && isLoading) {
                    showProcess(null);
                } else {
                    hideProcess();
                }

            });


            articleViewModel.getArticleResponse().observe(this, apiResponse -> {
                articleAdapter = new ArticleAdapter(this, apiResponse);
                Toolbar toolbar = findViewById(R.id.tool_bar);
                toolbar.setTitle(apiResponse.getTitle());
                articleRecyclerView.setAdapter(articleAdapter);
                if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*call the api while pull to refresh*/
    @Override
    public void onRefresh() {
        initWidget();
    }
}
