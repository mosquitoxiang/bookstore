package com.example.administrator.modelmall.ui.activities;

import android.app.ProgressDialog;
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
import com.example.administrator.modelmall.entity.LoginData;
import com.example.administrator.modelmall.ui.customview.StatusBarUtils;
import com.example.administrator.modelmall.utils.DialogUtils;
import com.example.administrator.modelmall.utils.HttpUtils;
import com.example.administrator.modelmall.utils.MD5Utils;
import com.example.administrator.modelmall.utils.RegularVerification;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

import static java.lang.Thread.sleep;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.tv_email)
    public TextInputEditText mEmail;
    @BindView(R.id.tv_pwd)
    public TextInputEditText mPwd;

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_to_register)
    TextView tvToRegister;
    private String nickName;
    private String email;
    private String pwd;

    @Override
    public Object offerLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void onBindView() {
        hideActionBar();
        StatusBarUtils.setWindowStatusBarColor(this, R.color.orange);
//        getDataFromDB();

        btnLogin.setOnClickListener(this);
        tvToRegister.setOnClickListener(this);


    }

    /**
     * 核对登陆信息
     */
    private boolean checkUserInputData() {
        boolean isPass = true;
        email = mEmail.getText().toString();
        pwd = mPwd.getText().toString();

        if (email.isEmpty() || !RegularVerification.isEmail(email)) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }
        if (pwd.isEmpty() || pwd.length() < 6) {
            mPwd.setError("请输入至少6位密码");
            isPass = false;
        }

        return isPass;
    }

    @Override
    public void destory() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                email = mEmail.getText().toString();
                pwd = mPwd.getText().toString();
                String message = "请稍后...";

                final String LOGIN_ADDRESS = ModelConstant.LOGIN+"email=" +email+"&password="+pwd;
                DialogUtils.showProgressDialog(this, message, new DialogUtils.OnRunningListenter() {
                    @Override
                    public void onRunning(final ProgressDialog dialog) {
                        HttpUtils.sendOkHttpRequest(LOGIN_ADDRESS, new okhttp3.Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                try {
                                    sleep(3000);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                                dialog.dismiss();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "请检查网络是否错误", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return;
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                try {
                                    sleep(3000);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                                dialog.dismiss();
                                String responseData = response.body().string();
                                Gson gson = new Gson();
                                LoginBean loginAccout = gson.fromJson(responseData, LoginBean.class);
                                LoginBean.DataBean data = loginAccout.getData();
                                int code = loginAccout.getCode();
                                Log.d(TAG, "onResponseCode: " + code);
                                if (code != 200) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(LoginActivity.this, "登陆失败,请检查邮箱密码是否正确", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    return;
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                email = data.getEmail();
                                nickName = data.getNickname();
                                if (email == null || nickName == null){
                                    return;
                                }
                                EventBus.getDefault().postSticky(new LoginData(email, nickName));
                                Log.d(TAG, "onResponse: "+email+nickName);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
//                    if (email.equals(dbEmail) && pwd.equals(dbPwd)) {
//                        ToastUtils.showToast(this,"登陆成功",ToastUtils.LENGTH_LONG);
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        finish();
//                    } else {
//                        ToastUtils.showToast(this,"账号或者密码有误",ToastUtils.LENGTH_LONG);
//                    }

                break;
            case R.id.tv_to_register:
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                break;
        }

    }
}
