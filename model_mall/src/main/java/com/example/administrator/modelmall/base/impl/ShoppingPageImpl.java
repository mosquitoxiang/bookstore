package com.example.administrator.modelmall.base.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.adapter.ShopCartAdapter;
import com.example.administrator.modelmall.base.BasePage;
import com.example.administrator.modelmall.events.GoodInfoMsg;
import com.example.administrator.modelmall.ui.customview.ToastUtils;
import com.kongzue.dialog.v2.SelectDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ShoppingPageImpl extends BasePage implements View.OnClickListener {

    private static final String TAG = "ShoppingPageImpl";

    private RecyclerView recyclerView;
    private ShopCartAdapter adapter;
    private List<Integer> mList;
    private ImageView imgRmpty;
    private TextView tvClear;//清空
    private TextView tvDel;  //删除

    private TextView tvSummary;//结算
    private TextView tvTital;


    public ShoppingPageImpl(Context context) {
        super(context);
    }

    @Override
    public Object setContentView() {
        return R.layout.page_shopping;
    }

    @Override
    public void init() {
        EventBus.getDefault().register(this); // 传this , 传 context 报错
        mList = new ArrayList<>();
        Log.d(TAG, "init: "+mList.size());
        recyclerView = view.findViewById(R.id.shop_cart);
        imgRmpty = view.findViewById(R.id.img_empty);

        tvClear = view.findViewById(R.id.tv_clear);
        tvDel = view.findViewById(R.id.tv_del);
        tvSummary = view.findViewById(R.id.tv_summary);
        tvTital = view.findViewById(R.id.tv_tital);

        tvClear.setOnClickListener(this);
        tvDel.setOnClickListener(this);
        tvSummary.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ShopCartAdapter(context, mList);
        recyclerView.setAdapter(adapter);


    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void Event(GoodInfoMsg goodInfoMsg) {
        imgRmpty.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        tvTital.setText("￥75.00");

        Logger.d("收到消息：通知显示商品" + goodInfoMsg.getGoodCount());
        int size = goodInfoMsg.getGoodCount();//没啥鸡用  必须写
        mList.add(size);// 没啥鸡用   必须写
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_clear:
                if (mList.size() == 0 || mList == null){
                    return;
                }
                showDialog();
                break;
            case R.id.tv_del:
                ToastUtils.showToast(context, "程序员小哥哥正在熬夜加班", ToastUtils.LENGTH_SHORT);
                break;
            case R.id.tv_summary:
                if (recyclerView.getVisibility() == View.VISIBLE){
                    ToastUtils.showToast(context, "调用支付宝", ToastUtils.LENGTH_SHORT);
                }else{
                    ToastUtils.showToast(context, "购物车为空，请先购买商品", ToastUtils.LENGTH_SHORT);
                }

                break;
        }
    }

    private void showDialog() {
        SelectDialog.show(context, "Warning", "是否清除购物车", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.clearData(mList);
                ToastUtils.showToast(context, "清除商品成功", ToastUtils.LENGTH_SHORT);
                adapter.notifyDataSetChanged();
            }
        }, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "您点击了取消按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
