package com.example.administrator.modelmall.ui.activities;


import android.content.Intent;

import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.modelmall.Constant.ModelConstant;
import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.db.UserRegisterBean;

import com.example.administrator.modelmall.entity.LoginBean;
import com.example.administrator.modelmall.entity.RegisterBean;
import com.example.administrator.modelmall.ui.customview.StatusBarUtils;
import com.example.administrator.modelmall.ui.customview.ToastUtils;
import com.example.administrator.modelmall.utils.HttpUtils;
import com.example.administrator.modelmall.utils.MD5Utils;
import com.example.administrator.modelmall.utils.RegularVerification;
import com.google.gson.Gson;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class SignupActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_name)
    public TextInputEditText nickName;
    @BindView(R.id.tv_email)
    public TextInputEditText userEmail;
    @BindView(R.id.tv_pwd)
    public TextInputEditText userPwd;


    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_to_login)
    TextView toLogin;
    private String name;
    private String email;
    private String pwd;
    private static final String TAG = "SignupActivity";


    @Override
    public Object offerLayout() {
        return R.layout.activity_sign_up;
    }


    @Override
    public void onBindView() {
        hideActionBar();
        StatusBarUtils.setWindowStatusBarColor(this, R.color.orange);
        btnRegister.setOnClickListener(this);
        toLogin.setOnClickListener(this);
    }


    /**
     * 用户信息校验
     */
    private boolean checkInfo() {
//        // 获得用户输入信息
//        name = nickName.getText().toString();
//        email = userEmail.getText().toString();
//        pwd = userPwd.getText().toString();

        boolean isPass = true; // 是否通过检测
        if (name.isEmpty()) {
            nickName.setError("请输入昵称");
            isPass = false;
        }

        if (email.isEmpty() || !RegularVerification.isEmail(email)) {
            userEmail.setError("错误的邮箱格式");
            isPass = false;
        }


        if (pwd.isEmpty() || pwd.length() < 6) {
            userPwd.setError("请输入至少6位密码");
            isPass = false;
        }

        return isPass;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
//                if (checkInfo()){
                    name = nickName.getText().toString();
                    email = userEmail.getText().toString();
                    pwd = userPwd.getText().toString();
                    String URL = ModelConstant.REGISTER+"email=" + email + "&nickname=" + name +"&password="+pwd;
                    Log.d(TAG, "onClick: "+URL);
                    HttpUtils.sendOkHttpRequest(URL, new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SignupActivity.this, "注册异常", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData = response.body().string();
                            requestServer(responseData);
                        }
                    });
//                }
                break;
            case R.id.tv_to_login:
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                break;
        }
    }

    private void requestServer(String responseData) {
        Gson gson = new Gson();
        RegisterBean registerBean = gson.fromJson(responseData, RegisterBean.class);
        int code = registerBean.getCode();
        if (code != 200) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SignupActivity.this, "账号已存在", Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void destory() {

    }
}
