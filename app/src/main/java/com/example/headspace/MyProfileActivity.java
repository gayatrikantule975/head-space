package com.example.headspace;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

public class MyProfileActivity extends AppCompatActivity {
    boolean doubleTap = false;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ImageView imageView;
    Button btnChangeprofile;
    TextView tvName,tvMoNo,tvUsername,tvEmail,tvBirthDate,tvPassword,tvConfirmpass;
    Uri imagePath;
    Bitmap bitmap;
TextView tvToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        preferences = PreferenceManager.getDefaultSharedPreferences(MyProfileActivity.this);
        editor = preferences.edit();
        imageView = findViewById(R.id.ivMyProfilephoto);
        btnChangeprofile = findViewById(R.id.btnChangeProfile);
        tvName = findViewById(R.id.tvMyProfileName);
        tvMoNo = findViewById(R.id.tvMyProfileMoNo);
        tvEmail = findViewById(R.id.tvMyProfileEmailId);
        tvUsername = findViewById(R.id.tvMyProfileUserName);
        tvPassword = findViewById(R.id.tvMyProfilePassword);
        tvConfirmpass = findViewById(R.id.tvMyProfileConfirmPassword);
        tvBirthDate = findViewById(R.id.tvMyProfileBirthDate);
        String strName = preferences.getString("Name", "");
        String strMoNo = preferences.getString("MoNumber", "");
        String strUserName = preferences.getString("UserName", "");
        String strEmail = preferences.getString("Email", "");
        String strBirthD = preferences.getString("BirthDate", "");
        String strPass = preferences.getString("Password", "");
        String strConfirmPas = preferences.getString("ConfirmPass", "");
        tvName.setText(strName);
        tvMoNo.setText(strMoNo);
        tvUsername.setText(strUserName);
        tvEmail.setText(strEmail);
        tvBirthDate.setText(strBirthD);
        tvPassword.setText(strPass);
        tvConfirmpass.setText(strConfirmPas);
        tvToken = findViewById(R.id.tvMyProfileToken);
        btnChangeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        Toast.makeText(MyProfileActivity.this, "My Profile Activity Opened", Toast.LENGTH_SHORT).show();
        FirebaseMessaging.getInstance().getToken().
                addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MyProfileActivity.this, "Token is not recieved", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        tvToken.setText(token);
                        Toast.makeText(MyProfileActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void openImageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Profile Photo"), 999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==999&&resultCode==RESULT_OK&&data!=null)
        {
            imagePath=data.getData() ;
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MyProfileActivity.this, HomeActivity.class);
        startActivity(i);
    }
}


