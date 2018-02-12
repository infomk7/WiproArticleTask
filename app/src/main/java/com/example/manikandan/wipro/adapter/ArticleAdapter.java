package com.example.manikandan.wipro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manikandan.wipro.R;
import com.example.manikandan.wipro.network.model.ArticleAPIResponse;
import com.example.manikandan.wipro.network.model.ArticleRows;

/*Article Data List Adapter*/
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    Context context;
    ArticleAPIResponse articleList;

    public ArticleAdapter(Context context, ArticleAPIResponse itemList) {
        this.context = context;
        this.articleList = itemList;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item_list, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        try {
            ArticleRows list = articleList.getRows().get(position);
            holder.txtTitle.setText(list.getTitle());
            holder.txtDesc.setText(list.getDescription());
        /*Show Default Place holder before Image Loading*/
            Glide.with(context).load(list.getImageHref()).
                    placeholder(R.drawable.ic_all_inclusive_black_24dp).
                    error(R.drawable.ic_all_inclusive_black_24dp).skipMemoryCache(true).into(holder.imgProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return articleList.getRows().size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDesc;
        ImageView imgProfile;

        public ArticleViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txt_title);
            txtDesc = view.findViewById(R.id.txt_desc);
            imgProfile = view.findViewById(R.id.img_profile);
        }
    }
}
