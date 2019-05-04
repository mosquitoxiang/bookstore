package com.example.administrator.modelmall.ui.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.modelmall.R;
import com.example.administrator.modelmall.adapter.CommentAdapter;
import com.example.administrator.modelmall.entity.Comment;
import com.example.administrator.modelmall.entity.CommentMessageEvent;
import com.example.administrator.modelmall.utils.KeyboardUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommentActivity extends AppCompatActivity {

    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.user)
    TextView user;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.findBigImg)
    ImageView findBigImg;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.button)
    Button button;

    private static final String TAG = "CommentActivity";

    List<Comment> commentList;

    CommentAdapter adapter;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setImmersionStatusBar();
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        commentList = new ArrayList<>();
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentAdapter(this, commentList);
        rvComment.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEvent(CommentMessageEvent commentMessageEvent) {
        int position = commentMessageEvent.getPosition();
        imgHead.setImageResource(commentMessageEvent.getFindPageBeanList().get(position).getHeadResId());
        user.setText(commentMessageEvent.getFindPageBeanList().get(position).getUserName());
        time.setText(commentMessageEvent.getFindPageBeanList().get(position).getTime());
        findBigImg.setImageResource(commentMessageEvent.getFindPageBeanList().get(position).getFindBigResId());
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        String text = editText.getText().toString();
        Comment comment = new Comment(text);
        commentList.add(comment);
        adapter.notifyDataSetChanged();
        editText.setText("");
        KeyboardUtils.hideKeyboard(button);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
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
}
