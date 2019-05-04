package com.example.administrator.modelmall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.entity.Comment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyHolder>{

    Context context;

    List<Comment> commentList;

    Date now;

    SimpleDateFormat simpleDateFormat;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public CommentAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        return new CommentAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.MyHolder holder, int position) {
        now = new Date();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(now);
        Comment comment = commentList.get(position);
        holder.textView.setText(comment.getText());
        holder.tvTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView tvTime;

        public MyHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_comment);
            tvTime = itemView.findViewById(R.id.tv_date_comment_item);
        }
    }
}
