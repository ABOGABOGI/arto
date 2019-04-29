package com.example.cyber_net.apps.menu.ppob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.apps.Config;
import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.adapter.spiner.SpinnerPulsaAdapter;
import com.example.cyber_net.apps.model.Inquiry;
import com.example.cyber_net.apps.model.PULSA_create;
import com.example.cyber_net.apps.model.Pulsa;
import com.example.cyber_net.apps.network.ApiClient;
import com.example.cyber_net.apps.network.ApiInterface;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class Listrik extends AppCompatActivity {

    @BindView(R.id.rb_token)
    RadioButton rbToken;
    @BindView(R.id.rb_tsgihsn)
    RadioButton rbTsgihsn;
    @BindView(R.id.edt_id)
    EditText edt_id;
    @BindView(R.id.sp_nominal)
    Spinner spNominal;
    @BindView(R.id.ln_token)
    LinearLayout lnToken;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.ln_nominal)
    LinearLayout lnNominal;
    String link,item,user_id;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listrik);
        ButterKnife.bind(this);
        link = Config.BASE_URL;
        sharedPreferences = getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("id","Not Available");
        //dummy
        // Array of choices
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

        getSupportActionBar().setTitle("Listrik");
        getSupportActionBar().setSubtitle("Bayar Tagihan Listrik");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //dummy
        load_pulsa("26");

        if (rg.getCheckedRadioButtonId() == -1) {
            //no radi checked
        } else {
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rb_token:
                            //Toast.makeText(Listrik.this, "token", Toast.LENGTH_SHORT).show();
                            lnNominal.setVisibility(View.VISIBLE);
                            break;
                        case R.id.rb_tsgihsn:
                            //Toast.makeText(Listrik.this, "taguhan", Toast.LENGTH_SHORT).show();
                            lnNominal.setVisibility(View.GONE);
                            break;
                        default:
                            break;

                    }
                }
            });
        }
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
            if(rbTsgihsn.isChecked()){
                inquiry();
            }else{
                bayar();
            }
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
        Call<Inquiry> call = apiInterface.ppob_inquiry("PLN",nomor);
        call.enqueue(new Callback<Inquiry>() {
            @Override
            public void onResponse(Call<Inquiry> call, Response<Inquiry> response) {
                String nama = response.body().getNama();
                String reffid = response.body().getReff_id();
                if(reffid==null){
                    Toast.makeText(Listrik.this,"Nomor Pelanggan Tidak Ditemukan",Toast.LENGTH_LONG).show();
                }else{
                    String jumlah = response.body().getJumlah();
                    String customer = response.body().getCustomer();
                    String transaksi = response.body().getNomor();
                    Intent intent = new Intent(Listrik.this, Detail_ppob.class);
                    intent.putExtra("nama",nama);
                    intent.putExtra("reffid",reffid);
                    intent.putExtra("jumlah",jumlah);
                    intent.putExtra("produk","PLN");
                    intent.putExtra("customer",customer);
                    intent.putExtra("nomor",transaksi);
                    intent.putExtra("title","Listrik");
                    intent.putExtra("subtitle","Bayar Tagihan Listrik");
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Inquiry> call, Throwable t) {
            }
        });
    }
    private void load_pulsa(String kode_pulsa){
        ApiInterface apiInterface = ApiClient.getRetrofit(link)
                .create(ApiInterface.class);
        Call<com.example.cyber_net.apps.model.Pulsa> call = apiInterface.getPulsalist(kode_pulsa);
        call.enqueue(new Callback<com.example.cyber_net.apps.model.Pulsa>() {
            @Override
            public void onResponse(Call<com.example.cyber_net.apps.model.Pulsa> call, Response<com.example.cyber_net.apps.model.Pulsa> response) {
                com.example.cyber_net.apps.model.Pulsa pd = response.body();
                List<Pulsa.Datum> pda = pd.data;
                SpinnerPulsaAdapter spa = new SpinnerPulsaAdapter(Listrik.this, R.layout.one_item_spinner, pda);
                spNominal.setAdapter(spa);
                spNominal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        item = ((TextView)view.findViewById(R.id.one_item_id)).getText().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<com.example.cyber_net.apps.model.Pulsa> call, Throwable t) {

            }
        });
    }
    private void bayar(){
        String customer = edt_id.getText().toString();
        ApiInterface apiInterface = ApiClient.getRetrofit(link)
                .create(ApiInterface.class);
        Call<PULSA_create> call = apiInterface.pulsa_create(item,user_id,customer);
        call.enqueue(new Callback<PULSA_create>() {
            @Override
            public void onResponse(Call<PULSA_create> call, Response<PULSA_create> response) {
                String status = response.body().getStatus();
                edt_id.setText("");
                if(status.equals("00")){
                    String res_harga = NumberFormat.getInstance().format(response.body().getHarga_jual()).toString();
                    String res_sn = response.body().getSn();
                    Toast.makeText(Listrik.this,"Pembelian Berhasil Dengan SN "+res_sn+"  Dengan Jumlah "+res_harga,LENGTH_LONG).show();
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                    sendBroadcast(broadcastIntent);
                }else{
                    Toast.makeText(Listrik.this,"Pembayaran Gagal ",LENGTH_LONG).show();
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                    sendBroadcast(broadcastIntent);
                }
            }

            @Override
            public void onFailure(Call<PULSA_create> call, Throwable t) {
            }
        });
    }
}
