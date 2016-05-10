package com.cz.circleprogressbar;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    ActionBar mActionBar;

    @Bind(R.id.progress)
    ProgressView mProgressView;

    int currentProgress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();

        mActionBar.setTitle("进度条");


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (currentProgress <= 100) {

                    Message message = new Message();
                    message.arg1 = currentProgress;
                    handler.sendMessage(message);

                    currentProgress++;

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mProgressView.setCurrentProgress(msg.arg1);

        }
    };
}
