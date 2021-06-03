package com.example.news;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    MusicService.MyBinder binder;
    private EditText path;
    private myConn conn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        path = findViewById(R.id.et_inputpath);
        findViewById(R.id.tv_play).setOnClickListener(this);
        findViewById(R.id.tv_pause).setOnClickListener(this);
        conn = new myConn();
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    //点击按钮执行的方法
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        String pathway = path.getText().toString().trim();
        File SDpath = Environment.getExternalStorageDirectory();
        File file = new File(SDpath, pathway);
        String path = file.getAbsolutePath();
        //动态申请读取存储权限
        ActivityCompat.requestPermissions(MusicActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        switch (v.getId()) {
            //点击播放按钮执行的方法
            case R.id.tv_play:
                if (file.exists() && file.length() > 0) {
                    binder.play(path);
                } else {
                    Toast.makeText(this, "找不到音乐文件", Toast.LENGTH_SHORT).show();
                }
                break;
            //点击暂停按钮执行的方法
            case R.id.tv_pause:
                binder.pause();
                break;
            default:
                break;
        }
    }

    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

    //绑定服务
    private class myConn implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MusicService.MyBinder) service;
        }

        public void onServiceDisconnected(ComponentName name) {
        }
    }
}
