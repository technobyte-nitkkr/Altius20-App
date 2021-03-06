package com.nitkkr.altius;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitkkr.altius.root.IntroSlider;
import com.nitkkr.altius.root.UserLogin;

public class MainActivitySplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT= 2500;
    private ImageView splashScreen;
    private TextView splashText;
    Animation shake,topAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        //hooks
//        splashText=findViewById(R.id.splash_text);
        splashScreen = findViewById(R.id.imageView2);
        //animation loading
    //    shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
    //    topAnim=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.top_tobot);


   //     splashScreen.startAnimation(topAnim);
    //    splashText.startAnimation(shake);



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
