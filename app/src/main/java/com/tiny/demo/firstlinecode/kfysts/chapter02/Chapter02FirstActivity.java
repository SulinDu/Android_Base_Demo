package com.tiny.demo.firstlinecode.kfysts.chapter02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

public class Chapter02FirstActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, Chapter02FirstActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter02_first);
        LogUtils.INSTANCE.e("MultipleProcessActivity First","sUserId --> " +MultipleProcessActivity.sUserId);
    }
}
