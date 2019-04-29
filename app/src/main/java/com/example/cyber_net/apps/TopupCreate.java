package com.example.cyber_net.apps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyber_net.apps.model.*;
import com.example.cyber_net.apps.model.Topup;
import com.example.cyber_net.apps.network.ApiClient;
import com.example.cyber_net.apps.network.ApiInterface;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopupCreate extends AppCompatActivity {
    @BindView(R.id.edt_nominal)
    EditText edtNominal;
    String link,id,idva;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_create);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Topup");
        getSupportActionBar().setSubtitle("Topup Saldo Anda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        link = Config.BASE_URL;
        sharedPreferences = getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","0");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    public void ajukan_topup(View view){
        String nominal = edtNominal.getText().toString();
        ApiInterface apiInterface = ApiClient.getRetrofit(link).create(ApiInterface.class);
        Call<com.example.cyber_net.apps.model.Topup> call = apiInterface.topup_create(id,nominal);
        call.enqueue(new Callback<Topup>() {
            @Override
            public void onResponse(Call<com.example.cyber_net.apps.model.Topup> call, Response<Topup> response) {
                if(response.body().getApi_status()==1) {
                    Toast.makeText(TopupCreate.this, "Berhasil Ajukan Topup, Silahkan Transfer ke Rekening Tujuan", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(TopupCreate.this, "Gagal Mengajukan Topup", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.cyber_net.apps.model.Topup> call, Throwable t) {
                Log.i("error",t.toString());
            }
        });
    }
}
