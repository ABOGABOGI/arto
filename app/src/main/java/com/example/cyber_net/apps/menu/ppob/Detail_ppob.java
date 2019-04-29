package com.example.cyber_net.apps.menu.ppob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.apps.Config;
import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.model.PPOB_payment;
import com.example.cyber_net.apps.network.ApiClient;
import com.example.cyber_net.apps.network.ApiInterface;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class Detail_ppob extends AppCompatActivity {
String nama,reffid,produk,customer,jumlah,link,nomor,user_id;
    @BindView(R.id.detail_nomor)
    TextView detail_nomor;
    @BindView(R.id.detail_nama)
    TextView detail_nama;
    @BindView(R.id.detail_jumlah)
    TextView detail_jumlah;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ppob);
        ButterKnife.bind(this);
        String title = getIntent().getStringExtra("title");
        String subtitle = getIntent().getStringExtra("subtitle");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subtitle);
        nama = getIntent().getStringExtra("nama");
        reffid = getIntent().getStringExtra("reffid");
        produk = getIntent().getStringExtra("produk");
        customer = getIntent().getStringExtra("customer");
        jumlah = getIntent().getStringExtra("jumlah");
        nomor = getIntent().getStringExtra("nomor");
        NumberFormat formatter = new DecimalFormat("#,###");
        Integer nominal = Integer.parseInt(jumlah);
        detail_nomor.setText("Nomor : "+customer);
        detail_nama.setText("Nama : "+nama);
        detail_jumlah.setText("Jumlah : "+formatter.format(nominal));
        link = Config.BASE_URL;
        sharedPreferences = getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("id","Not Available");
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
        getMenuInflater().inflate(R.menu.kirim, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.send) {
            bayar();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bayar(){
        ApiInterface apiInterface = ApiClient.getRetrofit(link)
                .create(ApiInterface.class);
        Call<PPOB_payment> call = apiInterface.ppob_payment(produk,reffid,customer,jumlah,nomor,user_id);
        call.enqueue(new Callback<PPOB_payment>() {
            @Override
            public void onResponse(Call<PPOB_payment> call, Response<PPOB_payment> response) {
                String res_nama = response.body().getNama();
                String res_total = response.body().getTotal_tagihan();
                String res_biller = response.body().getBiller_ref();
                String status = response.body().getStatus();
                if(status.equals("00")){
                    Toast.makeText(Detail_ppob.this,"Pembayaran Berhasil Dengan Nomor "+res_biller+" Atas Nama "+res_nama+" Dengan Jumlah "+res_total,LENGTH_LONG).show();
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                    sendBroadcast(broadcastIntent);
                }else{
                    Toast.makeText(Detail_ppob.this,"Pembayaran Gagal "+status,LENGTH_LONG).show();
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                    sendBroadcast(broadcastIntent);
                }
            }

            @Override
            public void onFailure(Call<PPOB_payment> call, Throwable t) {
            }
        });
    }
}
