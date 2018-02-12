package com.example.manikandan.wipro.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*About Canada Article Response Model GSON class*/

public class ArticleAPIResponse {

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("rows")
    @Expose
    List<ArticleRows> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ArticleRows> getRows() {
        return rows;
    }

    public void setRows(List<ArticleRows> rows) {
        this.rows = rows;
    }




}
