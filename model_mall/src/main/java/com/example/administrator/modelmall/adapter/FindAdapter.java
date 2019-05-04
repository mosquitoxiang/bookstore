package com.example.administrator.modelmall.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.entity.FindPageBean;
import com.example.administrator.modelmall.ui.customview.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by SunnyDay on 2019/03/25
 * <p>
 * 发现页面的adapter
 */
public class FindAdapter extends RecyclerView.Adapter<FindAdapter.MyHolder> {


    private Context context;

    List<FindPageBean> findPageBeanList;

    public FindAdapter(Context context, List<FindPageBean> findPageBeanList) {
        this.context = context;
        this.findPageBeanList = findPageBeanList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_findpage, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        FindPageBean findPageBean = findPageBeanList.get(position);
        holder.tvUser.setText(findPageBean.getUserName());
        holder.imgHead.setImageResource(findPageBean.getHeadResId());
        holder.time.setText(findPageBean.getTime());
        holder.findBigImg.setImageResource(findPageBean.getFindBigResId());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(position);
            }
        });
    }

    OnItemClickListener listener;

    public void setOnItemClick(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void click(int position);
    }

    @Override
    public int getItemCount() {
        return findPageBeanList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private CardView card;
        private ImageView imgHead;
        private TextView tvUser;
        private TextView time;
        private ImageView findBigImg;

        public MyHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardview);
            imgHead = itemView.findViewById(R.id.img_head);
            tvUser = itemView.findViewById(R.id.user);
            time = itemView.findViewById(R.id.time);
            findBigImg = itemView.findViewById(R.id.findBigImg);
        }
    }
}
