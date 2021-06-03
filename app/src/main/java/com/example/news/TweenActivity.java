package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class TweenActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        //初始化组件并加添点击事件
        Button buttonOne = (Button) findViewById(R.id.btn_one);
        Button buttonTwo = (Button) findViewById(R.id.btn_two);
        Button buttonThree = (Button) findViewById(R.id.btn_three);
        Button buttonFour = (Button) findViewById(R.id.btn_four);
        ivBean = (ImageView) findViewById(R.id.iv_bean);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                //调用AnimationUtils的loadAnimation()方法加载动画
                //点击按钮使图片渐变
                Animation alpha = AnimationUtils.loadAnimation(this,
                        R.anim.alpha_animation);
                ivBean.startAnimation(alpha);
                break;
            case R.id.btn_two:
                //点击按钮使图片旋转
                Animation rotate = AnimationUtils.loadAnimation(this,
                        R.anim.rotate_animation);
                ivBean.startAnimation(rotate);
                break;
            case R.id.btn_three:
                //点击按钮使图片缩放
                Animation scale = AnimationUtils.loadAnimation(this,
                        R.anim.scale_animation);
                ivBean.startAnimation(scale);
                break;
            case R.id.btn_four:
                //点击按钮使图片移动
                Animation translate = AnimationUtils.loadAnimation(this,
                        R.anim.translate_animation);
                ivBean.startAnimation(translate);
                break;
        }
    }
}