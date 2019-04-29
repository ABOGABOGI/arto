package com.example.cyber_net.apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyber_net.apps.model.Akun;
import com.example.cyber_net.apps.network.ApiClient;
import com.example.cyber_net.apps.network.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class Signin extends AppCompatActivity {
    @BindView(R.id.edt_rgs_nama)
    EditText edtNama;
    @BindView(R.id.edt_rgs_email)
    EditText edtEmail;
    @BindView(R.id.edt_rgs_pass)
    EditText edtPass;
    String link;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("SIGN IN");
        getSupportActionBar().setSubtitle("Daftarkan Akun Anda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        link = Config.BASE_URL;
    }
    public void proses_signin(View v){
        String nama = edtNama.getText().toString();
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();

        ApiInterface apiInterface = ApiClient.getRetrofit(link)
                .create(ApiInterface.class);
        Call<Akun> call = apiInterface.sign_in(nama,email,pass);
        call.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                Integer status = response.body().getId();
                if(status!=null){
                    edtEmail.setText("");
                    edtPass.setText("");
                    edtNama.setText("");
                    Toast.makeText(Signin.this,"Sign Berhasil, Silahkan Login",LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(Signin.this,"Sign Gagal",LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
            }
        });
    }
}
