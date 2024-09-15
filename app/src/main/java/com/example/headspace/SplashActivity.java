package com.example.headspace;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class SplashActivity extends AppCompatActivity {
    ImageView ivLogo;
    Animation fadeInAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivLogo = findViewById(R.id.ivMainLogo);
        fadeInAnim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadein);
        ivLogo.setAnimation(fadeInAnim);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, Aftersplash.class);
                startActivity(i);
                finish();
            }
        },4000);
    }
}