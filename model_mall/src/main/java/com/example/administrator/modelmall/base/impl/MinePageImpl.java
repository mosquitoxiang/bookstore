package com.example.administrator.modelmall.base.impl;

import android.content.Context;
import android.content.Intent;
import android.print.PrinterId;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.modelmall.Constant.ModelConstant;
import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.base.BasePage;
import com.example.administrator.modelmall.entity.ChangeNameBean;
import com.example.administrator.modelmall.entity.LoginData;
import com.example.administrator.modelmall.ui.activities.AboutActivity;
import com.example.administrator.modelmall.ui.customview.ToastUtils;
import com.example.administrator.modelmall.utils.HttpUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class MinePageImpl extends BasePage implements View.OnClickListener {

    private EditText editNickNamePageMine;
    private ImageView imgChangeNamePageMine;
    private ImageView imgSureNamePageMine;
    private static final String TAG = "MinePageImpl";

    String email;
    String nickName;

    public MinePageImpl(Context context) {
        super(context);
    }

    @Override
    public Object setContentView() {
        return R.layout.page_mine;
    }

    @Override
    public void init() {
        imgSureNamePageMine = view.findViewById(R.id.img_sure_name_page_mine);
        editNickNamePageMine = view.findViewById(R.id.edit_nick_name_page_mine);
        imgChangeNamePageMine = view.findViewById(R.id.img_change_name_page_mine);
        imgChangeNamePageMine.setImageResource(R.drawable.change);
        editNickNamePageMine.setEnabled(false);
        RelativeLayout myGoods = view.findViewById(R.id.rl_my_goods);
        RelativeLayout myGoodsAddr = view.findViewById(R.id.rl_goods_addr);
        RelativeLayout setting = view.findViewById(R.id.rl_setting);
        myGoods.setOnClickListener(this);
        myGoodsAddr.setOnClickListener(this);
        setting.setOnClickListener(this);
        imgChangeNamePageMine.setOnClickListener(this);
        imgSureNamePageMine.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_my_goods:
                ToastUtils.showToast(context, "待续", ToastUtils.LENGTH_SHORT);
                break;
            case R.id.rl_goods_addr:
                ToastUtils.showToast(context, "待续", ToastUtils.LENGTH_SHORT);
                break;
            case R.id.rl_setting:
                context.startActivity(new Intent(context, AboutActivity.class));
                break;
            case R.id.img_change_name_page_mine:
                imgChangeNamePageMine.setVisibility(View.GONE);
                imgSureNamePageMine.setVisibility(View.VISIBLE);
                editNickNamePageMine.setEnabled(true);
                break;
            case R.id.img_sure_name_page_mine:
                imgSureNamePageMine.setVisibility(View.GONE);
                imgChangeNamePageMine.setVisibility(View.VISIBLE);
                editNickNamePageMine.setEnabled(false);
                requestDate();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(LoginData loginData){
        email = loginData.getEmail();
        nickName = loginData.getNickName();
        editNickNamePageMine.setText(nickName.toCharArray(), 0, nickName.length());
        Log.d(TAG, "onEvent: "+email+nickName);
    }

    private void requestDate() {
        nickName = editNickNamePageMine.getText().toString();
        String URL_ADDRESS = ModelConstant.CHANGE_NICKNAME+"email=" +email+"&nickname="+nickName;
        HttpUtils.sendOkHttpRequest(URL_ADDRESS, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                ChangeNameBean changeNameBean = new Gson().fromJson(responseData, ChangeNameBean.class);
                int code = changeNameBean.getCode();
                if (code != 200){
                    return;
                }else{
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}
