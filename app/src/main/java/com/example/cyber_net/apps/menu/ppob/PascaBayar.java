package com.example.cyber_net.apps.menu.ppob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cyber_net.apps.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PascaBayar extends AppCompatActivity {

    @BindView(R.id.sp_tagihan)
    Spinner spTagihan;
    @BindView(R.id.edt_id)
    EditText edtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasca_bayar);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Pasca Bayar");
        getSupportActionBar().setSubtitle("Tagihan Pasca Bayar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //dummy
        // Array of choices
        String colors[] = {"Tagihan HALO - Telkomsel Pasca Bayar", "Tagihan MATRIX - Indosat Pasca Bayar",
                "Tagihan ESIA - Esia Pasca Bayar" };

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colors);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spTagihan.setAdapter(spinnerArrayAdapter);
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
            Toast.makeText(this, "Kirim", Toast.LENGTH_SHORT).show();
            return true;
        }/* else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }*/
        return super.onOptionsItemSelected(item);
    }
}
