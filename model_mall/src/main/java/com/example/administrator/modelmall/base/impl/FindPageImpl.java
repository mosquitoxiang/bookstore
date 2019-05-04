package com.example.administrator.modelmall.base.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.adapter.FindAdapter;
import com.example.administrator.modelmall.base.BasePage;
import com.example.administrator.modelmall.entity.CommentMessageEvent;
import com.example.administrator.modelmall.entity.FindPageBean;
import com.example.administrator.modelmall.ui.activities.AboutActivity;
import com.example.administrator.modelmall.ui.activities.CommentActivity;
import com.example.administrator.modelmall.ui.activities.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class FindPageImpl extends BasePage {

    private static final String TAG = "FindPageImpl";

    private RecyclerView recyclerView;

    FindAdapter findAdapter;

    List<FindPageBean> findPageBeanList;

    public FindPageImpl(Context context) {
        super(context);
    }

    @Override
    public Object setContentView() {
        return R.layout.page_find;
    }

    @Override
    public void init() {
        initData();
        recyclerView = view.findViewById(R.id.recycler_find);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        findAdapter = new FindAdapter(context, findPageBeanList);
        recyclerView.setAdapter(findAdapter);
        findAdapter.setOnItemClick(new FindAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                context.startActivity(new Intent(context, CommentActivity.class));
                Log.d(TAG, "click: "+findPageBeanList.size());
                EventBus.getDefault().postSticky(new CommentMessageEvent(findPageBeanList, position));
            }
        });
    }

    private void initData() {
        findPageBeanList = new ArrayList<>();
        FindPageBean findPageBean1 = new FindPageBean(R.drawable.headprofit1, "测试1", "星期一 8:00", R.drawable.book1);
        findPageBeanList.add(findPageBean1);
        FindPageBean findPageBean2 = new FindPageBean(R.drawable.headprofit2, "测试2", "星期二 9:00", R.drawable.book2);
        findPageBeanList.add(findPageBean2);
        FindPageBean findPageBean3 = new FindPageBean(R.drawable.headprofit3, "测试3", "星期三 10:00", R.drawable.book3);
        findPageBeanList.add(findPageBean3);
        FindPageBean findPageBean4 = new FindPageBean(R.drawable.headprofit4, "猜测4", "星期四 11:00", R.drawable.book4);
        findPageBeanList.add(findPageBean4);
        FindPageBean findPageBean5 = new FindPageBean(R.drawable.headprofit5, "测试5", "星期五 12:00", R.drawable.book5);
        findPageBeanList.add(findPageBean5);
    }

}
