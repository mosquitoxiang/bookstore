package com.example.administrator.modelmall.base.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.modelmall.Constant.ModelConstant;
import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.adapter.BookListAdapter;
import com.example.administrator.modelmall.base.BasePage;
import com.example.administrator.modelmall.db.BookListDB;
import com.example.administrator.modelmall.entity.BookList;
import com.example.administrator.modelmall.ui.activities.BookDetails;
import com.example.administrator.modelmall.ui.customview.ToastUtils;
import com.example.administrator.modelmall.utils.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ListPageImpl extends BasePage implements BookListAdapter.OnBookListItemClickListener {

    private static final String TAG = "ListPageImpl";

    RecyclerView rvActivityBookList;

    BookListAdapter adapter;

    List<BookList.DataBean> dataBeanList;

    SwipeRefreshLayout mRefreshLayout;

    private String bookName;

    public ListPageImpl(Context context) {
        super(context);
    }

    @Override
    public Object setContentView() {
        return R.layout.activity_book_list;
    }

    @Override
    public void init() {

        mRefreshLayout = view.findViewById(R.id.srl_refresh_activity_book_list);
        initRefreshLayout();
        dataBeanList = new ArrayList<>();
        rvActivityBookList = view.findViewById(R.id.rv_activity_book_list);
        rvActivityBookList.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BookListAdapter(context, dataBeanList);
        rvActivityBookList.setAdapter(adapter);

        adapter.setOnListClick(this);
        getDataFromServer();
    }

    private void initRefreshLayout() {
        // 刷新进度条颜色
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        // 刷新进度条位置设置
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
        // 下拉刷新事件监听
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

    }

    private void refreshData() {
        HttpUtils.sendOkHttpRequest(ModelConstant.BOOK_LIST, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(context, "刷新失败", ToastUtils.LENGTH_LONG);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dataBeanList.clear();
                String responseData = response.body().string();
                BookList bookList = new Gson().fromJson(responseData, BookList.class);
                dataBeanList = bookList.getData();
                int code = bookList.getCode();
                if (code != 200) {
                    return;
                }

                view.post(new Runnable() {
                    @Override
                    public void run() {

                        adapter.setData(dataBeanList);
                        adapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                        ToastUtils.showToast(context, "刷新完毕", ToastUtils.LENGTH_LONG);
                    }
                });

            }
        });
    }

    private void getDataFromServer() {
        HttpUtils.sendOkHttpRequest(ModelConstant.BOOK_LIST, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.d(TAG, "onResponse: " + responseData);
                handleResponseData(responseData);
            }
        });
    }

    private void handleResponseData(String responseData) {
        BookList bookList = new Gson().fromJson(responseData, BookList.class);
        if (bookList == null) {
            return;
        }
        dataBeanList = bookList.getData();
        int code = bookList.getCode();
        Log.d(TAG, "handleResponseData: " + code);
        if (bookList == null) {
            return;
        }
        adapter.setData(dataBeanList);
//        BookListDB db = new BookListDB();
//        for (int i=0;i<dataBeanList.size();i++){
//            db.setAuthor(dataBeanList.get(i).getAuthor());
//            db.setBookInfo(dataBeanList.get(i).getBook_info());
//            db.setBookName(dataBeanList.get(i).getBookname());
//            db.setClassName(dataBeanList.get(i).getClass_name());
//            db.setStartName(dataBeanList.get(i).getStat_name());
//            db.setUrl(dataBeanList.get(i).getBook_cover());
//            db.save();
//        }
    }

    @Override
    public void click(int position) {
        if (dataBeanList == null || dataBeanList.size() == 0) {
            return;
        }
        Intent intent = new Intent(context, BookDetails.class);
        bookName = dataBeanList.get(position).getBookname();
        intent.putExtra(ModelConstant.BOOK_NAME, bookName);
        context.startActivity(intent);
    }


}
