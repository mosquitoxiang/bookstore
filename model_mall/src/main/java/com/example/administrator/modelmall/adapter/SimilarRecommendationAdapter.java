package com.example.administrator.modelmall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.entity.BookDetailsBean;

import java.util.List;

public class SimilarRecommendationAdapter extends RecyclerView.Adapter<SimilarRecommendationAdapter.MyHolder> {

    Context context;

    List<BookDetailsBean.DataBeanX.DataBean> dataBeanList;

    public SimilarRecommendationAdapter(Context context, List<BookDetailsBean.DataBeanX.DataBean> dataBeans) {
        this.context = context;
        this.dataBeanList = dataBeans;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_similar_recommendation, parent, false);
        return new SimilarRecommendationAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        BookDetailsBean.DataBeanX.DataBean dataBean = dataBeanList.get(position);
        holder.tvBookName.setText(dataBean.getTitle());
        holder.tvAuthor.setText(dataBean.getAuthor());
        String url = dataBean.getCover();
        Glide.with(context).load(url).into(holder.imgBook);
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView imgBook;
        TextView tvBookName, tvAuthor;
        public MyHolder(View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.img_book_similar_recommendation);
            tvBookName = itemView.findViewById(R.id.tv_book_name_item_similar_recommendation);
            tvAuthor = itemView.findViewById(R.id.tv_author_item_similar_recommendation);
        }
    }

    public void setData(List<BookDetailsBean.DataBeanX.DataBean> dataBeanList){
        this.dataBeanList = dataBeanList;
    }
}
