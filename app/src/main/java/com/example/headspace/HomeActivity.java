package com.example.headspace;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {
    boolean doubleTap = false;
    BottomNavigationView bottomNavigationView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home Page");

        preferences = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        editor = preferences.edit();

        boolean firstTime = preferences.getBoolean("isFirstTime", true);
        if (firstTime) {
            welcome();
        }
        bottomNavigationView = findViewById(R.id.homeBottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.btmHome);
    }

    private void welcome() {
        Dialog dialog=new Dialog(HomeActivity.this);
       dialog.setContentView(R.layout.customdialog);
       dialog.setCancelable(false);
        Button button=dialog.findViewById(R.id.btnWelcome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        editor.putBoolean("isFirstTime", false).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.homeMenuQr)
        {
            Intent i=new Intent(HomeActivity.this,QrCodeActivity.class);
            startActivity(i);
        }
       else if(item.getItemId()==R.id.itLocation)
        {
            Intent i=new Intent(HomeActivity.this,LocationActivity.class);
            startActivity(i);
        }
        else
        if (item.getItemId()== R.id.homeMenuMyProfile) {
            Intent i = new Intent(HomeActivity.this, MyProfileActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.homeMenuSettings) {
            Intent  i= new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.homeMenuContactUs) {
            Intent i = new Intent(HomeActivity.this, ContactUsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.homeMenuAboutUs) {
            Intent i = new Intent(HomeActivity.this, AboutUsActivity.class);
            startActivity(i);
        } else if (item.getItemId()== R.id.homeMenuLogout) {
            logout();
        }
        return true;
    }

    private void logout() {
        AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("HeadSpace");
        ad.setMessage("Are you sure you want to logout?");
        ad.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        ad.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor.putBoolean("isLogin", false).commit();
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }).create().show();
    }

    @Override
    public void onBackPressed() {

        if (doubleTap) {
            super.onBackPressed();
            finishAffinity();
        } else {
            Toast.makeText(HomeActivity.this, "Double tap to exit", Toast.LENGTH_SHORT).show();
            doubleTap = true;
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleTap = false;
                }
            }, 2000);
        }
    }
    HomeFragment homeFragment=new HomeFragment();
    MoodTrackerFragment moodTrackerFragment=new MoodTrackerFragment();
    WellnessFragment wellnessFragment=new WellnessFragment();
    MusicFragment musicFragment=new MusicFragment();
    TimerFragment timerFragment=new TimerFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.btmHome)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame,homeFragment).commit();
        }
        else if(item.getItemId()==R.id.moodtracckermenu)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.homeFrame, moodTrackerFragment).commit();
        }
        else if(item.getItemId()==R.id.wellness)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame,wellnessFragment).commit();
        }
        else if(item.getItemId()==R.id.music)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame,musicFragment).commit();
        }
        else if(item.getItemId()==R.id.timer)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame,timerFragment).commit();
        }
        return true;
    }
}
