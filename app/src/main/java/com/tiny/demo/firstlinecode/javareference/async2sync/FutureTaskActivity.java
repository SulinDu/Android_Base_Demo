package com.tiny.demo.firstlinecode.javareference.async2sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ThreadUtils;
import com.tiny.demo.firstlinecode.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: Java并发编程：Callable、Future和FutureTask
 * 异步转同步
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/18 5:49 PM
 * @Version TODO
 */
public class FutureTaskActivity extends AppCompatActivity {
    public static final String TAG = FutureTaskActivity.class.getSimpleName();


    @BindView(R.id.btn_java_callable_future)
    Button btnJavaCallableFuture;
    @BindView(R.id.btn_java_callable_futureTask)
    Button btnJavaCallableFutureTask;
    @BindView(R.id.btn_java_callable_futureTask1)
    Button btnJavaCallableFutureTask1;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, FutureTaskActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_task);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_java_callable_future)
    public void onBtnJavaCallableFutureClicked() {
        // Callable + Future + ExecutorService
        ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main start");
        ExecutorService executorService = Executors.newCachedThreadPool();
        CustomCallable task = new CustomCallable();
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();

        ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main 111");

        try {
            LogUtils.INSTANCE.e(TAG, "task执行结果:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main 222:" + i);
        }

        ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main 主线程任务执行完毕");
    }

    class CustomCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            LogUtils.INSTANCE.e(TAG, "在子线程进行计算");
            ThreadUtils.INSTANCE.logCurrThreadName(TAG + " Task start");
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                sum += i;
                ThreadUtils.INSTANCE.logCurrThreadName(TAG + " Task sum:" + sum);
            }
            ThreadUtils.INSTANCE.logCurrThreadName(TAG + " Task end");
            return sum;
        }
    }

    @OnClick(R.id.btn_java_callable_futureTask)
    public void onBtnJavaCallableFutureTaskClicked() {
        // Callable + FutureTask + ExecutorService
        ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main start");
        ExecutorService executorService = Executors.newCachedThreadPool();
        CustomCallable task = new CustomCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executorService.submit(futureTask);
        executorService.shutdown();

        ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main 111");

        try {
            LogUtils.INSTANCE.e(TAG, "task执行结果:" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main 222:" + i);
        }

        ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main 主线程任务执行完毕");
    }

    @OnClick(R.id.btn_java_callable_futureTask1)
    public void onViewClicked() {
        // Callable + FutureTask + Thread
        ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main start");
        CustomCallable task = new CustomCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask);
        thread.start();

        ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main 111");

        try {
            LogUtils.INSTANCE.e(TAG, "task执行结果:" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main 222:" + i);
        }

        ThreadUtils.INSTANCE.logCurrThreadName(TAG + "  main 主线程任务执行完毕");
    }
}