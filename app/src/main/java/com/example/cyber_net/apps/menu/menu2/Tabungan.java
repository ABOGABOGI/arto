package com.example.cyber_net.apps.menu.menu2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.cyber_net.apps.MainActivity;
import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.adapter.NewsAdapter;
import com.example.cyber_net.apps.adapter.TabunganAdapter;
import com.example.cyber_net.apps.model.IsiTabungan;
import com.example.cyber_net.apps.model.News;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Tabungan extends AppCompatActivity {

    @BindView(R.id.txt_notif)
    TextView txtNotif;
    @BindView(R.id.txt_nama)
    TextView txtNama;
    @BindView(R.id.txt_nominal)
    TextView txtNominal;
    @BindView(R.id.rv_tabungan)
    RecyclerView rvTabungan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabungan);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Tabungan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getTabungan();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
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

    private void getTabungan() {
        try {

            rvTabungan.setLayoutManager(new LinearLayoutManager(this));
            ArrayList<IsiTabungan> animalNames = new ArrayList<>();
            animalNames.add(new IsiTabungan("tagar", R.drawable.tagar));
            animalNames.add(new IsiTabungan("tabsus", R.drawable.tabsus));
            animalNames.add(new IsiTabungan("tabum", R.drawable.tabum));
            animalNames.add(new IsiTabungan("tabah", R.drawable.tabah));
            animalNames.add(new IsiTabungan("pamor", R.drawable.pamor));
            animalNames.add(new IsiTabungan("zamrud", R.drawable.zamrud));
            animalNames.add(new IsiTabungan("tasbam", R.drawable.tasbam));

            // set up the RecyclerView
            TabunganAdapter adapter = new TabunganAdapter(this, animalNames);
            rvTabungan.setAdapter(adapter);
        } catch (Exception e) {

        }
    }

}