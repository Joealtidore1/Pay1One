package com.pay1onet.fmca.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pay1onet.fmca.R;

public class LauncherActivity extends AppCompatActivity {
     Runnable runnable = () -> {
         startActivity(new Intent(getApplicationContext(), SignInActivity.class));
         finish();
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation imageAnim = AnimationUtils.loadAnimation(this, R.anim.launcher_logo_anim);
        Animation lineUpAnim = AnimationUtils.loadAnimation(this, R.anim.line_up_anim);
        Animation lineDownAnim = AnimationUtils.loadAnimation(this, R.anim.line_down);
        ImageView logo = findViewById(R.id.logo);
        logo.setAnimation(imageAnim);
        View startLine = findViewById(R.id.startLine);
        View middleLine = findViewById(R.id.middleLine);
        View endLine = findViewById(R.id.endLine);
        View middleStart = findViewById(R.id.middleStart);
        View middleEnd = findViewById(R.id.middleEnd);
        middleLine.setAnimation(lineUpAnim);
        startLine.setAnimation(lineUpAnim);
        endLine.setAnimation(lineUpAnim);
        middleStart.setAnimation(lineDownAnim);
        middleEnd.setAnimation(lineDownAnim);
        lineDownAnim.setRepeatMode(Animation.RESTART);
        lineUpAnim.setRepeatCount(Animation.RESTART);
        lineUpAnim.setRepeatMode(Animation.RESTART);
        lineDownAnim.setRepeatCount(Animation.RESTART);

        new Handler().postDelayed(runnable, 2000);
    }
}