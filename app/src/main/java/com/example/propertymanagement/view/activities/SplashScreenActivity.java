package com.example.propertymanagement.view.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.helpers.SessionManager;

public class SplashScreenActivity extends AppCompatActivity {

    SessionManager sessionManager;
    ImageView imageView;

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        init();
    }

    private void init() {
        sessionManager = new SessionManager();

        imageView = findViewById(R.id.image_view_logo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        imageView.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sessionManager.checkLogin()){
                    Intent i=new Intent(SplashScreenActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }else{
                    sessionManager.goToLogin();
                }

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
