package com.example.cyber_net.apps.menu.menu2.item;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.adapter.menu2.KeretaAdapter;
import com.example.cyber_net.apps.model.Kereta;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.cyber_net.apps.helper.Constan.RESULT_BERANGKAT;
import static com.example.cyber_net.apps.helper.Constan.RESULT_PULANG;
import static com.example.cyber_net.apps.helper.Constan.VALUE_BERANGKAT;
import static com.example.cyber_net.apps.helper.Constan.VALUE_PULANG;

public class ListKereta extends AppCompatActivity implements SearchView.OnQueryTextListener, KeretaAdapter.OnFunction {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    KeretaAdapter adapter;
    List<Kereta> list;
    Intent intent;
    String cekKeberangkatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kereta);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Cari Kereta Api");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = new Intent();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        list.add(new Kereta("Jakarta"));
        list.add(new Kereta("Semarang"));

        Intent myIntent = getIntent(); // gets the previously created intent
        cekKeberangkatan = myIntent.getStringExtra(VALUE_BERANGKAT); // will return "FirstKeyValue"

        if (cekKeberangkatan == null) {
            cekKeberangkatan = myIntent.getStringExtra(VALUE_PULANG); // will return "FirstKeyValue"
        }

        Toast.makeText(this, cekKeberangkatan, Toast.LENGTH_SHORT).show();
        adapter = new KeretaAdapter(this, list, ListKereta.this);
        rvList.setAdapter(adapter);
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Cari");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<Kereta> filteredValues = new ArrayList<>(list);
        for (Kereta value : list) {
            if (!value.getNama().toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(value);
            }
        }

       adapter.updateList(filteredValues);
        return true;
    }

    @Override
    public void onSelected(Kereta data) {
        Toast.makeText(this, data.getNama(), Toast.LENGTH_SHORT).show();
        if (cekKeberangkatan.equalsIgnoreCase(VALUE_BERANGKAT)) {
            intent.putExtra(VALUE_BERANGKAT, data);
            //dan notif result untu startactivityforresult
            //dengan isi RESULT_UPDATE yang menandakan ini kode update
            setResult(RESULT_BERANGKAT, intent);
        } else if (cekKeberangkatan.equalsIgnoreCase(VALUE_PULANG)) {
            intent.putExtra(VALUE_PULANG, data);
            //dan notif result untu startactivityforresult
            //dengan isi RESULT_UPDATE yang menandakan ini kode update
            setResult(RESULT_PULANG, intent);
        }
        finish();
    }
}
