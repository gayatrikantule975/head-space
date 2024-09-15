package com.example.headspace;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegistrationActivity extends AppCompatActivity {
    LottieAnimationView lavId;
    EditText etName,etMono,etUser,etDate,etPass,etConfirm,etEmail;
    Button btnHome;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Register here");
        preferences= PreferenceManager.getDefaultSharedPreferences(RegistrationActivity.this);
        editor=preferences.edit();
        etName=findViewById(R.id.etName);
        etUser=findViewById(R.id.etUser);
        etMono=findViewById(R.id.etMono);
        etEmail=findViewById(R.id.etEmail);
        etDate=findViewById(R.id.etDate);
        etPass=findViewById(R.id.etPass);
        etConfirm=findViewById(R.id.etConfirm);
        btnHome=findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().isEmpty())
                {
                    etName.setError("Name cannot be Empty");
                }
                else if(!etName.getText().toString().matches(".*[A-Z].*")||!etName.getText().toString().matches(".*[a-z].*"))
                {
                    etName.setError("Name must contain one upper case and one lowercase letter");
                }
                else if(etMono.getText().toString().isEmpty()||etMono.getText().toString().length()!=10)
                {
                    etMono.setError("Mobile number cannot be empty and contain 10 digits");
                }
                else if(etUser.getText().toString().isEmpty()||etUser.getText().toString().length()<8)
                {
                    etUser.setError("user name cannot be empty ,must be greater than 8");
                }
                else if (!etUser.getText().toString().matches(".*[A-Z].*")||!etUser.getText().toString().matches(".*[a-z].*"))
                {
                    etUser.setError("User name must contain one upper case and one lowercase letter");
                }
                else if(etEmail.getText().toString().isEmpty())
                {
                    etEmail.setError("E-mail cannot be empty");
                }
                else if(!etEmail.getText().toString().matches(".*[@].*"))
                {
                    etEmail.setError("Please enter @ in emial  ");
                }
                else if(!etEmail.getText().toString().matches(".*[.com].*"))
                {
                    etEmail.setError("Please enter .com in email");
                }
                else if (etDate.getText().toString().isEmpty())
                {
                    etDate.setError("Birth Date cannot be empty");
                }
                else if(etPass.getText().toString().isEmpty())
                {
                    etPass.setError("password cannot be empty");
                }
                else if (etPass.getText().toString().length()<8)
                {
                    etPass.setError("Password must be greater than 8");
                }
                else if(!etPass.getText().toString().matches(".*[A-Z].*"))
                {
                    etPass.setError("password must contain one capital letter");
                }
                else if(!etPass.getText().toString().matches(".*[a-z].*"))
                {
                    etPass.setError("password must contain one small letter");
                }
                else if(!etPass.getText().toString().matches(".*[0-9].*"))
                {
                    etPass.setError("password must contain one number");
                }
                else if(!etPass.getText().toString().matches(".*[@$%#].*"))
                {
                    etPass.setError("Password must conatin symbol from @,#,%");
                }
                else if(etConfirm.getText().toString().isEmpty())
                {
                    etConfirm.setError("Confirm password cannot be empty");
                }
                else
                {
                    editor.putString("Name",etName.getText().toString()).commit();
                    editor.putString("MoNumber",etMono.getText().toString()).commit();
                    editor.putString("UserName",etUser.getText().toString()).commit();
                    editor.putString("Email",etEmail.getText().toString()).commit();
                    editor.putString("BirthDate",etDate.getText().toString()).commit();
                    editor.putString("Password",etPass.getText().toString()).commit();
                    editor.putString("ConfirmPass",etConfirm.getText().toString()).commit();
                    progressDialog=new ProgressDialog(RegistrationActivity.this);
                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("Login is in Process");
                    progressDialog.show();
                    UserRegistrationDetails();
                }
            }

            private void UserRegistrationDetails() {
                AsyncHttpClient client=new AsyncHttpClient();//Client-Server communication:over the internet transfer the data
                RequestParams params=new RequestParams();//Use to put the to the AsyncHttpClients
                params.put("name",etName.getText().toString());
                params.put("mobileno",etMono.getText().toString());
                params.put("emailid",etEmail.getText().toString());
                params.put("username",etUser.getText().toString());
                params.put("password",etPass.getText().toString());
                client.post("http://192.168.61.27:80/headspaceAPI/userregistrationdeatils.php",params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        String status= null;
                        try {
                            status = response.getString("success");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        if(status.equals("1"))
                        {
                            Toast.makeText(RegistrationActivity.this,"Registration Done Successfully",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(RegistrationActivity.this,LocationActivity.class);
                            startActivity(i);
                            progressDialog.dismiss();
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(RegistrationActivity.this,"Registratin Failed",Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this,"Server Error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}