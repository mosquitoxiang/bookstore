package com.example.administrator.modelmall.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.entity.BookCategory;
import com.example.administrator.modelmall.entity.BookCategoryPosition;
import com.example.administrator.modelmall.ui.customview.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by SunnyDay on 2019/03/26
 */
public class SortLeftAdapter extends RecyclerView.Adapter<SortLeftAdapter.MyViewHolder>{

    List<BookCategory>  categoryList;

    private Context context;

    private int mPosition = 0;

    public void setPosition(int position){
        mPosition = position;
    }

    private Map<Integer,MyViewHolder> mHolderMap;

    public SortLeftAdapter(Context context, List<BookCategory> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
        mHolderMap = new HashMap<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sortpage_left, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        BookCategory bookCategory = categoryList.get(position);
        if (mPosition == position){
            holder.rlLayout.setBackgroundResource(R.color.categrory_bg);
            holder.tvSort.setTextColor(R.color.red);
            holder.tvGradient.setVisibility(View.VISIBLE);
        }else{
            holder.rlLayout.setBackgroundResource(R.color.white);
            holder.tvSort.setTextColor(R.color.black);
            holder.tvGradient.setVisibility(View.GONE);
        }
        holder.tvSort.setText(bookCategory.getName());
        holder.rlLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                // ToastUtils.showToast(context,"点击"+position,ToastUtils.LENGTH_LONG);
                //  发送通知更新消息
                EventBus.getDefault().post(new BookCategoryPosition(position));
                listener.onItemClick(view, position);
            }
        });
        mHolderMap.put(position, holder);
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvSort;
        RelativeLayout rlLayout;
        TextView tvGradient;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvSort = itemView.findViewById(R.id.tv_sort);
            rlLayout = itemView.findViewById(R.id.rl_layout);
            tvGradient = itemView.findViewById(R.id.img_gradient);
        }
    }

    OnItemClickListener listener;

    public void onClick(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

}
