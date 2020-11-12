package com.nitkkr.techspardha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nitkkr.techspardha.root.IntroSlider;
import com.nitkkr.techspardha.root.UserLogin;

public class MainActivitySplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT= 3000;
    private ImageView splashScreen;
    Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreen = findViewById(R.id.imageView2);

        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        splashScreen.startAnimation(shake);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
                if (!sp.getBoolean("first", false)) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("first", true);
                    editor.apply();
                    Intent intent = new Intent(MainActivitySplashScreen.this, IntroSlider.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent1 = new Intent(MainActivitySplashScreen.this, UserLogin.class);
                    startActivity(intent1);
                    finish();
                
                }

            }
        },SPLASH_TIME_OUT);

    }
}
