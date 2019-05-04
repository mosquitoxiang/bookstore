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

import com.bumptech.glide.Glide;
import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.entity.BookList;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyHolder> {

    List<BookList.DataBean> bookListList;

    Context context;

    public BookListAdapter(Context context, List<BookList.DataBean> bookListList) {
        this.context = context;
        this.bookListList = bookListList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book_list, parent, false);
        return new BookListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        BookList.DataBean bookList = bookListList.get(position);

        holder.tvBookName.setText(bookList.getBookname());
        holder.tvBookInfo.setText("书籍介绍:"+bookList.getBook_info());
        holder.tvStatName.setText(bookList.getStat_name());
        holder.tvClassName.setText(bookList.getClass_name());
        holder.tvAuthorName.setText("作者名:"+bookList.getAuthor_name());
        String url = bookList.getBook_cover();
        Glide.with(context).load(url).into(holder.imgBook);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(position);
            }
        });
    }

    OnBookListItemClickListener listener;

    public void setOnListClick(OnBookListItemClickListener listener){
        this.listener = listener;
    }

    public interface OnBookListItemClickListener{
        void click(int position);
    }

    @Override
    public int getItemCount() {
        return bookListList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private TextView tvBookName, tvBookInfo, tvStatName, tvClassName, tvAuthorName;
        private ImageView imgBook;
        private CardView cardView;

        public MyHolder(View itemView) {
            super(itemView);
            tvBookName = itemView.findViewById(R.id.tv_book_name_item_book_list);
            tvBookInfo = itemView.findViewById(R.id.tv_book_info_item_book_list);
            tvStatName = itemView.findViewById(R.id.tv_stat_name_item_book_list);
            tvClassName = itemView.findViewById(R.id.tv_class_name_item_book_list);
            tvAuthorName = itemView.findViewById(R.id.tv_author_name_item_book_list);
            imgBook = itemView.findViewById(R.id.img_book_item_book_list);
            cardView = itemView.findViewById(R.id.cardview_item_book_list);
        }
    }

    public void setData(List<BookList.DataBean> bookListList){
        this.bookListList = bookListList;
    }

}
