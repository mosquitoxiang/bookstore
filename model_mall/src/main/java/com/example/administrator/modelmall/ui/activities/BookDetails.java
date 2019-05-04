package com.example.administrator.modelmall.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.modelmall.Constant.ModelConstant;
import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.adapter.SimilarRecommendationAdapter;
import com.example.administrator.modelmall.entity.BookDetailsBean;
import com.example.administrator.modelmall.utils.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookDetails extends AppCompatActivity {

    private static final String TAG = "BookDetails";

    List<BookDetailsBean.DataBeanX.DataBean> dataBeanList;

    String bookName;
    @BindView(R.id.img_book_activity_book_details)
    ImageView imgBookActivityBookDetails;
    @BindView(R.id.tv_title_activity_book_details)
    TextView tvTitleActivityBookDetails;
    @BindView(R.id.tv_author_activity_book_details)
    TextView tvAuthorActivityBookDetails;
    @BindView(R.id.tv_tags_activity_book_details)
    TextView tvTagsActivityBookDetails;
    @BindView(R.id.tv_words_activity_book_details)
    TextView tvWordsActivityBookDetails;
    @BindView(R.id.tv_desc_activity_book_details)
    TextView tvDescActivityBookDetails;
    @BindView(R.id.tv_firtst_chapter)
    TextView tvFirtstChapter;
    @BindView(R.id.tv_latest_chapter)
    TextView tvLatestChapter;
    @BindView(R.id.rv_activity_book_details)
    RecyclerView rvActivityBookDetails;

    SimilarRecommendationAdapter adapter;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        ButterKnife.bind(this);
        setImmersionStatusBar();
        initView();
        Intent intent = getIntent();
        bookName = intent.getStringExtra(ModelConstant.BOOK_NAME);
        Log.d(TAG, "onCreate: " + bookName);
        getDataFromServer();
    }

    private void initView() {
        dataBeanList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvActivityBookDetails.setLayoutManager(layoutManager);
        adapter = new SimilarRecommendationAdapter(this, dataBeanList);
        rvActivityBookDetails.setAdapter(adapter);
    }

    /**
     * 沉浸式状态栏
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setImmersionStatusBar() {
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    private void getDataFromServer() {
        HttpUtils.sendOkHttpRequest(ModelConstant.BOOK_CONTENT + bookName, new Callback() {
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
        BookDetailsBean bookDetails = new Gson().fromJson(responseData, BookDetailsBean.class);
        BookDetailsBean.DataBeanX.AladdinBean data = bookDetails.getData().getAladdin();
        dataBeanList = bookDetails.getData().getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setData(dataBeanList);
                adapter.notifyDataSetChanged();
            }
        });
        Log.d(TAG, "handleResponseData: " + dataBeanList.get(0).getCover());
        final String title = data.getTitle();
        final String author = data.getAuthor();
        final String tags = data.getTags();
        final String desc = data.getDesc();
        final String url = data.getCover();
        final int words = data.getWords();
        final String firstChapter = data.getFirst_chapter().getCname();
        final String latest_chapter = data.getLatest_chapter().getCname();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvAuthorActivityBookDetails.setText("作者:"+author);
                tvDescActivityBookDetails.setText(desc);
                tvTagsActivityBookDetails.setText("标签:"+tags);
                tvWordsActivityBookDetails.setText(words + "字");
                tvTitleActivityBookDetails.setText(title);
                tvFirtstChapter.setText("第一章: " + firstChapter);
                tvLatestChapter.setText("最新章: " + latest_chapter);
                Glide.with(BookDetails.this).load(url).into(imgBookActivityBookDetails);
            }
        });
    }
}
