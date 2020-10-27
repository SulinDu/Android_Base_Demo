package com.tiny.demo.firstlinecode.kfysts.chapter03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: View的事件分发
 *
 * @author tiny
 * @date 2018/3/25 下午4:33
 */
public class Chapter0304Activity extends AppCompatActivity {
    @BindView(R.id.btn_test1)
    Button btnTest1;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Chapter0304Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter0304);
        ButterKnife.bind(this);
        View view = getLayoutInflater().inflate(R.layout.activity_chapter0304, null);
        LogUtils.INSTANCE.e("view --> " + view);

        getDecorView();
    }

    /**
     * 获取Activity所设置的View
     */
    private void getDecorView() {
        View rootView = ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
        LogUtils.INSTANCE.e("rootView --> " + rootView);
        rootView.setBackgroundColor(ContextCompat.getColor(Chapter0304Activity.this, R.color.gray_color));
    }

//    /**
//     * 事件分发伪代码
//     * @param ev
//     * @return
//     */
//    public boolean dispatchTouchEvent(MotionEvent ev){
//        boolean consume = false;
//        if(onInterceptTouchEvent(ev)){
//            consume = onTouchEvent(ev);
//        }else{
//            consume = child.dispatchTouchEvent(ev);
//        }
//        return consume;
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @OnClick(R.id.btn_test1)
    public void onViewClicked() {
        Chapter030401Activity.actionStart(this);
    }
}
