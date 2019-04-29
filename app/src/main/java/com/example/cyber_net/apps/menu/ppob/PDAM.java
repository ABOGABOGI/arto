package com.example.cyber_net.apps.menu.ppob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.apps.Config;
import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.adapter.spiner.CustomAdapter;
import com.example.cyber_net.apps.adapter.spiner.SpinnerListAdapter;
import com.example.cyber_net.apps.model.PPOB;
import com.example.cyber_net.apps.model.Pilihan;
import com.example.cyber_net.apps.model.Inquiry;
import com.example.cyber_net.apps.model.payment.Payment;
import com.example.cyber_net.apps.model.send.PostInquiry;
import com.example.cyber_net.apps.model.send.PostPayment;
import com.example.cyber_net.apps.network.ApiClient;
import com.example.cyber_net.apps.network.ApiInterface;
import com.example.cyber_net.apps.network.ApiService;
import com.example.cyber_net.apps.network.RetroClientInquiry;
import com.example.cyber_net.apps.network.RetroClientPayment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cyber_net.apps.helper.ConvertDate.tglDanJam;
import static com.example.cyber_net.apps.network.RetroClientInquiry.BASE_URL_INQUIRY;
import static com.example.cyber_net.apps.network.RetroClientInquiry.BASE_URL_PAYMENT;

public class PDAM extends AppCompatActivity {
    String link,id_produk;
    @BindView(R.id.sp_kereta)
    Spinner spKereta;
    @BindView(R.id.edt_id)
    EditText edt_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdam);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("PDAM");
        getSupportActionBar().setSubtitle("Bayar Tagihan Air");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        link = Config.BASE_URL;
        load_data();
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
    private void load_data(){
        ApiInterface apiInterface = ApiClient.getRetrofit(link)
                .create(ApiInterface.class);
        Call<PPOB> call = apiInterface.getPPOBlist(2);
        call.enqueue(new Callback<PPOB>() {
            @Override
            public void onResponse(Call<PPOB> call, Response<PPOB> response) {
                PPOB ppob = response.body();
                List<PPOB.Datum> datalist = ppob.data;
                SpinnerListAdapter adapter = new SpinnerListAdapter(PDAM.this, R.layout.one_item_spinner, datalist);
                spKereta.setAdapter(adapter);
                spKereta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = ((TextView)view.findViewById(R.id.one_item_id)).getText().toString();
                        id_produk = item;
//                        Toast.makeText(PDAM.this,item,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<PPOB> call, Throwable t) {

            }
        });
    }
    private void inquiry(){
        String nomor = edt_id.getText().toString();
        ApiInterface apiInterface = ApiClient.getRetrofit(link)
                .create(ApiInterface.class);
        Call<Inquiry> call = apiInterface.ppob_inquiry(id_produk,nomor);
        call.enqueue(new Callback<Inquiry>() {
            @Override
            public void onResponse(Call<Inquiry> call, Response<Inquiry> response) {
                String nama = response.body().getNama();
                String reffid = response.body().getReff_id();
                if(reffid==null){
                    Toast.makeText(PDAM.this,"Nomor Pelanggan Tidak Ditemukan",Toast.LENGTH_LONG).show();
                }else {
                    String jumlah = response.body().getJumlah();
                    String produk = response.body().getKode_produk();
                    String customer = response.body().getCustomer();
                    String transaksi = response.body().getNomor();
                    Intent intent = new Intent(PDAM.this, Detail_ppob.class);
                    intent.putExtra("nama", nama);
                    intent.putExtra("reffid", reffid);
                    intent.putExtra("jumlah", jumlah);
                    intent.putExtra("produk", id_produk);
                    intent.putExtra("customer", customer);
                    intent.putExtra("nomor", transaksi);
                    intent.putExtra("title", "PDAM");
                    intent.putExtra("subtitle", "Bayar Tagihan Air");
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Inquiry> call, Throwable t) {
            }
        });
    }
}
