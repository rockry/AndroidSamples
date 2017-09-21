package com.example.sanghyuknam.multithreadexample;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.example.sanghyuknam.multithreadexample.R.id.start_btn;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MultiThreadExample";
    TextView textView;
    Button start_btn;
    TextView textView2;
    Button start_btn2;
    Handler mHandler;
    private static int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        textView.setMovementMethod(new ScrollingMovementMethod());
        start_btn = (Button) findViewById(R.id.start_btn);
        start_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ExecutorService executorService = Executors.newFixedThreadPool(3);
                        executorService.execute(new MultiThread1());
                        executorService.execute(new MultiThread1());
                        executorService.execute(new MultiThread1());
                        executorService.execute(new MultiThread1());
                        executorService.execute(new MultiThread1());
                        executorService.execute(new MultiThread1());
                        executorService.shutdown();
                    }
                }
        );
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                String str = textView.getText().toString() + "\n";
                str += msg.what + ":" + msg.obj.toString();
                Log.d(TAG, msg.obj.toString());
                textView.setText(str);
            }
        };

        textView2 = (TextView) findViewById(R.id.textview2);
        textView2.setMovementMethod(new ScrollingMovementMethod());
        start_btn2 = (Button) findViewById(R.id.start_btn2);
        start_btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ExecutorService executorService = Executors.newFixedThreadPool(3);
                        Future<Integer> f1 =  executorService.submit(new MultiThread3());
                        Future<Integer> f2 =  executorService.submit(new MultiThread3());
                        Future<Integer> f3 =  executorService.submit(new MultiThread3());
                        Future<Integer> f4 =  executorService.submit(new MultiThread3());
                        Future<Integer> f5 =  executorService.submit(new MultiThread3());
                        Future<Integer> f6 =  executorService.submit(new MultiThread3());

                        String str = textView2.getText().toString();
                        try {
                            str += "\n" + f1.get() + "\n" + f2.get() + "\n" + f3.get() + "\n" + f4.get() + "\n" + f5.get() + "\n" + f6.get();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                        textView2.setText(str);
                        executorService.shutdown();
                    }
                }
        );
    }
    public class MultiThread1 implements Runnable {
        private int total;

        public void run() {
            for (int i = 1; i <= 2000000; i++) {
                total += i;
            }
            mHandler.sendMessage(mHandler.obtainMessage(cnt++, total));
        }
    }

    public class MultiThread3 implements Callable<Integer> {
        private int total;

        public Integer call() throws Exception {
            for (int i = 1; i <= 2000000; i++) {
                total += i;
            }
            return total;
        }
    }
}