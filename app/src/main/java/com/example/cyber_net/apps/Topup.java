package com.example.cyber_net.apps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.apps.network.ApiClient;
import com.example.cyber_net.apps.network.ApiInterface;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Topup extends AppCompatActivity {
        @BindView(R.id.tv_ket)
        TextView tvKet;
        @BindView(R.id.tv_nominal)
        TextView tvNominal;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_tanggal)
        TextView tvTanggal;
        @BindView(R.id.btn_batal)
        Button btnBatal;
        @BindView(R.id.btn_status)
        Button btnStatus;
        String link,id,idva;
        SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Topup");
        getSupportActionBar().setSubtitle("Topup Saldo Anda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        link = Config.BASE_URL;
        sharedPreferences = getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","0");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.package.ACTION_LOGOUT");
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("onReceive","Logout in progress");
                //At this point you should start the login activity and finish this one
                finish();
            }
        }, intentFilter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tambah, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.tambah) {
            if(idva == null){
                Intent intent = new Intent(Topup.this,TopupCreate.class);
                startActivity(intent);
            }else{
                Toast.makeText(Topup.this,"Masih Terdapat Topup yang Belum di Proses",Toast.LENGTH_LONG).show();
            }
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    public void get_topup(){
        ApiInterface apiInterface = ApiClient.getRetrofit(link).create(ApiInterface.class);
        Call<com.example.cyber_net.apps.model.Topup> call = apiInterface.topup_list(id);
        call.enqueue(new Callback<com.example.cyber_net.apps.model.Topup>() {
            @Override
            public void onResponse(Call<com.example.cyber_net.apps.model.Topup> call, Response<com.example.cyber_net.apps.model.Topup> response) {
                idva = response.body().getId();
                if(idva != null){
                    String sourceString = "Segera Transfer ke bank <b>CIMB NIAGA</b> dengan nomor rekening <b>1179009665400914</b> atas nama <b>IPAYMU</b>. Dengan keterangan sebagai berikut.";
                    tvKet.setText(Html.fromHtml(sourceString));
                    NumberFormat formatter = new DecimalFormat("#,###");
                    tvNominal.setText("Nominal : "+formatter.format(response.body().getNominal()));
                    tvStatus.setText("Status : "+response.body().getStatus());
                    tvTanggal.setText("Tanggal Pengajuan : "+response.body().getTanggal());
                    btnBatal.setVisibility(View.VISIBLE);
                    btnStatus.setVisibility(View.VISIBLE);
                }else{
                    tvKet.setText("Tambah data Topup Anda Terlebih Dahulu");
                    tvNominal.setText("Nominal : 0");
                    tvStatus.setText("Status : Tidak Ada");
                    tvTanggal.setText("Tanggal Pengajuan : TIdak Ada");
                    btnBatal.setVisibility(View.GONE);
                    btnStatus.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<com.example.cyber_net.apps.model.Topup> call, Throwable t) {
                Log.i("error",t.toString());
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        get_topup();
    }
    public void cek_status(View v){
        ApiInterface apiInterface = ApiClient.getRetrofit(link).create(ApiInterface.class);
        Call<com.example.cyber_net.apps.model.Topup> call = apiInterface.topup_cek(idva);
        call.enqueue(new Callback<com.example.cyber_net.apps.model.Topup>() {
            @Override
            public void onResponse(Call<com.example.cyber_net.apps.model.Topup> call, Response<com.example.cyber_net.apps.model.Topup> response) {
                String status = response.body().getStatus();
                if(status.equals("Berhasil")){
                    Toast.makeText(Topup.this,"TOPUP telah berhasil di proses",Toast.LENGTH_LONG).show();
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                    sendBroadcast(broadcastIntent);
                }else{
                    Toast.makeText(Topup.this,"Anda Belum Transfer ke Rekening Tujuan",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.cyber_net.apps.model.Topup> call, Throwable t) {
                Log.i("error",t.toString());
            }
        });
    }
    public void batal(View v){
        ApiInterface apiInterface = ApiClient.getRetrofit(link).create(ApiInterface.class);
        Call<com.example.cyber_net.apps.model.Topup> call = apiInterface.topup_batal(idva);
        call.enqueue(new Callback<com.example.cyber_net.apps.model.Topup>() {
            @Override
            public void onResponse(Call<com.example.cyber_net.apps.model.Topup> call, Response<com.example.cyber_net.apps.model.Topup> response) {
                String status = response.body().getStatus();
                if(status.equals("Batal")){
                    Toast.makeText(Topup.this,"TOPUP telah Dibatalkan",Toast.LENGTH_LONG).show();
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                    sendBroadcast(broadcastIntent);
                }else{
                    Toast.makeText(Topup.this,"TOPUP gagal dibatalkan",Toast.LENGTH_LONG).show();
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                    sendBroadcast(broadcastIntent);
                }
            }

            @Override
            public void onFailure(Call<com.example.cyber_net.apps.model.Topup> call, Throwable t) {
                Log.i("error",t.toString());
            }
        });
    }

}
