package com.example.cyber_net.apps.menu.menu2;

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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.apps.Config;
import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.adapter.spiner.SpinnerCustomAdapter;
import com.example.cyber_net.apps.adapter.spiner.SpinnerListAdapter;
import com.example.cyber_net.apps.adapter.spiner.SpinnerListAdapter2;
import com.example.cyber_net.apps.adapter.spiner.SpinnerPulsaAdapter;
import com.example.cyber_net.apps.menu.ppob.TV;
import com.example.cyber_net.apps.model.Kategori_pulsa;
import com.example.cyber_net.apps.model.PPOB;
import com.example.cyber_net.apps.model.PULSA_create;
import com.example.cyber_net.apps.network.ApiClient;
import com.example.cyber_net.apps.network.ApiInterface;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class Pulsa extends AppCompatActivity {

    @BindView(R.id.sp_operator)
    Spinner spOperator;
    @BindView(R.id.sp_nominal)
    Spinner spNominal;
    @BindView(R.id.btn_pulsa)
    ImageButton btnPulsa;
    @BindView(R.id.v_1)
    View v1;
    @BindView(R.id.btn_data)
    ImageButton btnData;
    @BindView(R.id.v_2)
    View v2;
    @BindView(R.id.txt_botif)
    TextView txtBotif;
    @BindView(R.id.pulsa_harga)
    TextView txtHarga;
    String link,user_id;
    SharedPreferences sharedPreferences;
    SpinnerListAdapter2 pulsa;
    SpinnerListAdapter2 data;
    @BindView(R.id.edt_id) TextView edt_nomor;
    String item = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulsa);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Pulsa");
        getSupportActionBar().setSubtitle("Isi Pulsa Dan Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        link = Config.BASE_URL;
        load_data_pulsa();
        load_data_data();
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
//            Toast.makeText(this, "Kirim ", Toast.LENGTH_SHORT).show();
            bayar();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btn_pulsa, R.id.btn_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pulsa:
                txtBotif.setText("ISI PULSA");
                btnPulsa.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                btnData.setColorFilter(getResources().getColor(R.color.black));
                v1.setBackgroundColor(getResources().getColor(R.color.kuning));
                v2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                spOperator.setAdapter(pulsa);
                spOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = ((TextView)view.findViewById(R.id.one_item_id)).getText().toString();
//                        Toast.makeText(Pulsa.this,item,Toast.LENGTH_LONG).show();
                        load_pulsa(item);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                break;
            case R.id.btn_data:
                txtBotif.setText("ISI DATA");
                btnData.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                btnPulsa.setColorFilter(getResources().getColor(R.color.black));
                v1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                v2.setBackgroundColor(getResources().getColor(R.color.kuning));
                spOperator.setAdapter(data);
                spOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = ((TextView)view.findViewById(R.id.one_item_id)).getText().toString();
//                        Toast.makeText(Pulsa.this,item,Toast.LENGTH_LONG).show();
                        load_pulsa(item);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                break;
        }
    }
    private void load_data_pulsa(){
        ApiInterface apiInterface = ApiClient.getRetrofit(link)
                .create(ApiInterface.class);
        Call<Kategori_pulsa> call = apiInterface.getKategorilist("Pulsa");
        call.enqueue(new Callback<Kategori_pulsa>() {
            @Override
            public void onResponse(Call<Kategori_pulsa> call, Response<Kategori_pulsa> response) {
                Kategori_pulsa kategori_pulsa = response.body();
                List<Kategori_pulsa.Datum> datalist = kategori_pulsa.data;
                pulsa = new SpinnerListAdapter2(Pulsa.this, R.layout.one_item_spinner, datalist);
                spOperator.setAdapter(pulsa);
                spOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = ((TextView)view.findViewById(R.id.one_item_id)).getText().toString();
//                        Toast.makeText(Pulsa.this,item,Toast.LENGTH_LONG).show();
                        load_pulsa(item);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Kategori_pulsa> call, Throwable t) {

            }
        });
    }
    private void load_data_data(){
        ApiInterface apiInterface = ApiClient.getRetrofit(link)
                .create(ApiInterface.class);
        Call<Kategori_pulsa> call = apiInterface.getKategorilist("Data");
        call.enqueue(new Callback<Kategori_pulsa>() {
            @Override
            public void onResponse(Call<Kategori_pulsa> call, Response<Kategori_pulsa> response) {
                Kategori_pulsa kategori_pulsa = response.body();
                List<Kategori_pulsa.Datum> datalist = kategori_pulsa.data;
                Log.v("pulsa",response.toString() );
                data = new SpinnerListAdapter2(Pulsa.this, R.layout.one_item_spinner, datalist);
            }

            @Override
            public void onFailure(Call<Kategori_pulsa> call, Throwable t) {

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
                List<com.example.cyber_net.apps.model.Pulsa.Datum> pda = pd.data;
                SpinnerPulsaAdapter spa = new SpinnerPulsaAdapter(Pulsa.this, R.layout.one_item_spinner, pda);
                spNominal.setAdapter(spa);
                spNominal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        item = ((TextView)view.findViewById(R.id.one_item_id)).getText().toString();
                        String harga = ((TextView)view.findViewById(R.id.one_item_harga)).getText().toString();
                        txtHarga.setText(harga);
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
        String customer = edt_nomor.getText().toString();
        ApiInterface apiInterface = ApiClient.getRetrofit(link)
                .create(ApiInterface.class);
        Call<PULSA_create> call = apiInterface.pulsa_create(item,user_id,customer);
        call.enqueue(new Callback<PULSA_create>() {
            @Override
            public void onResponse(Call<PULSA_create> call, Response<PULSA_create> response) {
                String status = response.body().getStatus();
                edt_nomor.setText("");
                if(status.equals("00")){
                    String res_harga = NumberFormat.getInstance().format(response.body().getHarga_jual()).toString();
                    String res_sn = response.body().getSn();
                    Toast.makeText(Pulsa.this,"Pembelian Berhasil Dengan SN "+res_sn+"  Dengan Jumlah "+res_harga,LENGTH_LONG).show();
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                    sendBroadcast(broadcastIntent);
                }else{
                    Toast.makeText(Pulsa.this,"Pembayaran Gagal ",LENGTH_LONG).show();
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