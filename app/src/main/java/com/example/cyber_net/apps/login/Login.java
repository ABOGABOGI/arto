package com.example.cyber_net.apps.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyber_net.apps.Config;
import com.example.cyber_net.apps.Home;
import com.example.cyber_net.apps.MainActivity;
import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.Signin;
import com.example.cyber_net.apps.model.Akun;
import com.example.cyber_net.apps.network.ApiClient;
import com.example.cyber_net.apps.network.ApiInterface;
import com.example.cyber_net.apps.session.Session;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cyber_net.apps.helper.Constan.USER_LOGIN1;
import static com.example.cyber_net.apps.helper.Constan.USER_LOGIN2;
import static com.example.cyber_net.apps.helper.Constan.USER_NAME;
import static com.example.cyber_net.apps.helper.Constan.USER_PASS;

public class Login extends AppCompatActivity {

    @BindView(R.id.ext_user)
    EditText edtUser;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    Session session;
    String link;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        link = Config.BASE_URL;
        sharedPreferences = getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
    }

    @OnClick({R.id.btn_login, R.id.btn_sigin, R.id.btn_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                proses_login();
                break;
            case R.id.btn_sigin:
                Intent intent = new Intent(Login.this, Signin.class);
                startActivity(intent);
                break;
            case R.id.btn_forget:
                break;
        }
    }
    public void proses_login(){
        String email = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        ApiInterface apiInterface = ApiClient.getRetrofit(link).create(ApiInterface.class);
        Call<Akun> call = apiInterface.login(email,pass);
        call.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                Log.i("status","response");
                String nama = response.body().getNama();
                String id = String.valueOf(response.body().getId());
                NumberFormat formatter = new DecimalFormat("#,###");
                String saldo = formatter.format(response.body().getSaldo());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id",id);
                editor.putString("nama",nama);
                editor.putString("saldo",saldo);
                editor.putBoolean("status", true);
                editor.commit();
                edtPass.setText("");
                edtUser.setText("");
                Toast.makeText(Login.this,"Login Berhasil",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Log.i("error",t.toString());
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        boolean loggedIn = sharedPreferences.getBoolean("status", false);

        //If we will get true
        if(loggedIn){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);

            //We will start the Profile Activity

        }
    }
}
