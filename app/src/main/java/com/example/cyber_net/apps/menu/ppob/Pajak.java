package com.example.cyber_net.apps.menu.ppob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyber_net.apps.Config;
import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.model.Inquiry;
import com.example.cyber_net.apps.network.ApiClient;
import com.example.cyber_net.apps.network.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pajak extends AppCompatActivity {
    @BindView(R.id.edt_id)
    EditText edt_id;
    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pajak);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("PBB Jabotabek");
        getSupportActionBar().setSubtitle("Bayar PBB Jabotabek");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        link = Config.BASE_URL;
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
            inquiry();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void inquiry(){
        String nomor = edt_id.getText().toString();
        ApiInterface apiInterface = ApiClient.getRetrofit(link)
                .create(ApiInterface.class);
        Call<Inquiry> call = apiInterface.ppob_inquiry("PBB",nomor);
        call.enqueue(new Callback<Inquiry>() {
            @Override
            public void onResponse(Call<Inquiry> call, Response<Inquiry> response) {
                String nama = response.body().getNama();
                String reffid = response.body().getReff_id();
                if(reffid==null){
                    Toast.makeText(Pajak.this,"Nomor Pelanggan Tidak Ditemukan",Toast.LENGTH_LONG).show();
                }else{
                    String jumlah = response.body().getJumlah();
                    String customer = response.body().getCustomer();
                    String transaksi = response.body().getNomor();
                    Intent intent = new Intent(Pajak.this, Detail_ppob.class);
                    intent.putExtra("nama",nama);
                    intent.putExtra("reffid",reffid);
                    intent.putExtra("jumlah",jumlah);
                    intent.putExtra("produk","PBB");
                    intent.putExtra("customer",customer);
                    intent.putExtra("nomor",transaksi);
                    intent.putExtra("title","PBB Jabotabek");
                    intent.putExtra("subtitle","Bayar PBB Jabotabek");
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Inquiry> call, Throwable t) {
            }
        });
    }
}
