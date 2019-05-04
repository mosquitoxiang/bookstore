package com.example.administrator.modelmall.base.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.example.administrator.modelmall.Constant.ModelConstant;
import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.adapter.SortLeftAdapter;
import com.example.administrator.modelmall.base.BasePage;
import com.example.administrator.modelmall.entity.BookCategory;
import com.example.administrator.modelmall.entity.BookCategoryBean;
import com.example.administrator.modelmall.entity.BookCategoryPosition;
import com.example.administrator.modelmall.ui.activities.GoodsInfoActivity;
import com.example.administrator.modelmall.utils.HttpUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class SortPageImpl extends BasePage implements View.OnClickListener{

    private RecyclerView rvLeft;
    private ImageView img1, img2, img3, img4, img5, img6, img7;
    private LinearLayout llRecomment;
    private LinearLayout llHotSort;
    SortLeftAdapter adapter;

    List<BookCategory> categoryList;


    public SortPageImpl(Context context) {
        super(context);
    }

    @Override
    public Object setContentView() {
        return R.layout.page_sort;
    }

    @Override
    public void init() {
        categoryList = new ArrayList<>();
        initData();
        EventBus.getDefault().register(this);
        rvLeft = view.findViewById(R.id.rv_left);
        theFisrtRequestServer();
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        img4 = view.findViewById(R.id.img4);
        img5 = view.findViewById(R.id.img5);
        img6 = view.findViewById(R.id.img6);
        img7 = view.findViewById(R.id.img7);
        llRecomment = view.findViewById(R.id.ll_recomment);
        llHotSort = view.findViewById(R.id.ll_hot_sort);


        img1.setOnClickListener(this);
        llRecomment.setOnClickListener(this);
        llHotSort.setOnClickListener(this);

        adapter = new SortLeftAdapter(context, categoryList);
        adapter.onClick(new SortLeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.setPosition(position);
                adapter.notifyDataSetChanged();

            }
        });
        rvLeft.setLayoutManager(new LinearLayoutManager(context));
        rvLeft.setAdapter(adapter);

    }

    private void theFisrtRequestServer() {
        HttpUtils.sendOkHttpRequest(ModelConstant.BOOK_CATEGORY+"0", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                BookCategoryBean bookCategoryBean = new Gson().fromJson(responseData, BookCategoryBean.class);
                final String img_1 = bookCategoryBean.getData().getMain_image();
                final String img_2 = bookCategoryBean.getData().getCategoryItem().getItem_image1();
                final String img_3 = bookCategoryBean.getData().getCategoryItem().getItem_image2();
                final String img_4 = bookCategoryBean.getData().getCategoryItem().getItem_image3();
                final String img_5 = bookCategoryBean.getData().getCategoryItem().getItem_image4();
                final String img_6 = bookCategoryBean.getData().getCategoryItem().getItem_image5();
                final String img_7 = bookCategoryBean.getData().getCategoryItem().getItem_image6();
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(context).load(img_1).into(img1);
                        Glide.with(context).load(img_2).into(img2);
                        Glide.with(context).load(img_3).into(img3);
                        Glide.with(context).load(img_4).into(img4);
                        Glide.with(context).load(img_5).into(img5);
                        Glide.with(context).load(img_6).into(img6);
                        Glide.with(context).load(img_7).into(img7);
                    }
                });
            }
        });
    }

    private void initData() {
        BookCategory book1 = new BookCategory("学习教育");
        categoryList.add(book1);
        BookCategory book2 = new BookCategory("人文社科");
        categoryList.add(book2);
        BookCategory book3 = new BookCategory("青春文学");
        categoryList.add(book3);
        BookCategory book4 = new BookCategory("儿童文学");
        categoryList.add(book4);
        BookCategory book5 = new BookCategory("励志成功");
        categoryList.add(book5);
        BookCategory book6 = new BookCategory("艺术修养");
        categoryList.add(book6);
        BookCategory book7 = new BookCategory("杂志期刊");
        categoryList.add(book7);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_recomment:
            case R.id.ll_hot_sort:
                context.startActivity(new Intent(context, GoodsInfoActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BookCategoryPosition bookCategoryPosition) {
        int position = bookCategoryPosition.getPosition();
        requestDataFromServe(position);
//        // 播放动画
//        lavLoading.setVisibility(View.VISIBLE);
//        lavLoading.setRepeatCount(1);
//        lavLoading.playAnimation();

    }

    private void requestDataFromServe(int position) {
        HttpUtils.sendOkHttpRequest(ModelConstant.BOOK_CATEGORY+position, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                BookCategoryBean bookCategoryBean = new Gson().fromJson(responseData, BookCategoryBean.class);
                final String img_1 = bookCategoryBean.getData().getMain_image();
                final String img_2 = bookCategoryBean.getData().getCategoryItem().getItem_image1();
                final String img_3 = bookCategoryBean.getData().getCategoryItem().getItem_image2();
                final String img_4 = bookCategoryBean.getData().getCategoryItem().getItem_image3();
                final String img_5 = bookCategoryBean.getData().getCategoryItem().getItem_image4();
                final String img_6 = bookCategoryBean.getData().getCategoryItem().getItem_image5();
                final String img_7 = bookCategoryBean.getData().getCategoryItem().getItem_image6();
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(context).load(img_1).into(img1);
                        Glide.with(context).load(img_2).into(img2);
                        Glide.with(context).load(img_3).into(img3);
                        Glide.with(context).load(img_4).into(img4);
                        Glide.with(context).load(img_5).into(img5);
                        Glide.with(context).load(img_6).into(img6);
                        Glide.with(context).load(img_7).into(img7);
                    }
                });
            }
        });
    }


}
