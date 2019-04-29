package com.example.cyber_net.apps.menu.menu2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.menu.menu2.item.ListKereta;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.cyber_net.apps.helper.Constan.REQUEST_BERANGKAT;
import static com.example.cyber_net.apps.helper.Constan.REQUEST_PULANG;
import static com.example.cyber_net.apps.helper.Constan.RESULT_BERANGKAT;
import static com.example.cyber_net.apps.helper.Constan.RESULT_PULANG;
import static com.example.cyber_net.apps.helper.Constan.VALUE_BERANGKAT;
import static com.example.cyber_net.apps.helper.Constan.VALUE_PULANG;
import static com.example.cyber_net.apps.helper.ConvertDate.cekDate;
import static com.example.cyber_net.apps.helper.ConvertDate.tglHariIni;
import static com.example.cyber_net.apps.helper.ConvertDate.ubahTanggal2;

public class Kereta extends AppCompatActivity {

    @BindView(R.id.txt_dari)
    TextView txtDari;
    @BindView(R.id.ln_dari)
    LinearLayout lnDari;
    @BindView(R.id.txt_ke)
    TextView txtKe;
    @BindView(R.id.ln_ke)
    LinearLayout lnKe;
    @BindView(R.id.txt_date_berangkat)
    TextView txtDateBerangkat;
    @BindView(R.id.opsi)
    Switch opsi;
    @BindView(R.id.txt_date_pulang)
    TextView txtDatePulang;
    @BindView(R.id.ln_pulang)
    LinearLayout lnPulang;
    @BindView(R.id.txt_penumpang)
    TextView txtPenumpang;
    @BindView(R.id.ln_penumpang)
    LinearLayout lnPenumpang;
    @BindView(R.id.btn_cari)
    Button btnCari;
    @BindView(R.id.btn_swap)
    FloatingActionButton btnSwap;
    private Calendar myCalendar;
    NumberPicker nbDewasa, nbBayi;
    Button btnSelesai;
    int valueNpDesawa, valueNpBayi;
    com.example.cyber_net.apps.model.Kereta kereta;
    boolean swap = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kereta);
        ButterKnife.bind(this);

        Typeface customfont= Typeface.createFromAsset(getAssets(),"fonts/roboto.ttf");
        txtDari.setTypeface(customfont);
        txtKe.setTypeface(customfont);
        txtDateBerangkat.setTypeface(customfont);
        txtDatePulang.setTypeface(customfont);
        txtPenumpang.setTypeface(customfont);
        btnCari.setTypeface(customfont);

        getSupportActionBar().setTitle("Kereta Api");
        getSupportActionBar().setSubtitle("Pesan Tiket Kereta Api");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myCalendar = Calendar.getInstance();
        txtDateBerangkat.setText(ubahTanggal2(tglHariIni()));
        txtDatePulang.setText(ubahTanggal2(tglHariIni()));

        opsi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lnPulang.setVisibility(View.VISIBLE);
                } else {
                    lnPulang.setVisibility(View.GONE);
                }
            }
        });
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

    @OnClick({R.id.ln_dari, R.id.ln_ke, R.id.txt_date_berangkat, R.id.txt_date_pulang, R.id.ln_pulang, R.id.ln_penumpang, R.id.btn_cari})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ln_dari:
                Intent intent = new Intent(Kereta.this, ListKereta.class);
                //buka activity dengan mengirim kode ke form
                intent.putExtra(VALUE_BERANGKAT, VALUE_BERANGKAT);
                startActivityForResult(intent, REQUEST_BERANGKAT);
                break;
            case R.id.ln_ke:
                Intent intent2 = new Intent(Kereta.this, ListKereta.class);
                //buka activity dengan mengirim kode ke form
                intent2.putExtra(VALUE_PULANG, VALUE_PULANG);
                startActivityForResult(intent2, REQUEST_PULANG);
                break;
            case R.id.txt_date_berangkat:
                new DatePickerDialog(Kereta.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "yyyy-MM-dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        txtDateBerangkat.setText(ubahTanggal2(sdf.format(myCalendar.getTime())));
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.txt_date_pulang:
                new DatePickerDialog(Kereta.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "yyyy-MM-dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        //hari
                        String d1 = txtDateBerangkat.getText().toString();
                        String d2 = ubahTanggal2(sdf.format(myCalendar.getTime()));

                        if (cekDate(d1, d2, "Tanggal berangkat tidak boleh lebih kecil dari pada tanggal pulang",
                                Kereta.this)){
                            return;
                        }else {
                            txtDatePulang.setText(ubahTanggal2(sdf.format(myCalendar.getTime())));
                        }
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.ln_pulang:
                break;
            case R.id.ln_penumpang:
                View view2 = getLayoutInflater().inflate(R.layout.select_penumpang_kereta, null);

                final BottomSheetDialog dialog = new BottomSheetDialog(Kereta.this);
                nbDewasa = view2.findViewById(R.id.nb_dewasa);
                nbBayi = view2.findViewById(R.id.nb_bayi);
                btnSelesai = view2.findViewById(R.id.btn_selesai);

                dialog.setContentView(view2);
                dialog.show();

                //number picker dewasa
                nbDewasa.setMinValue(0);
                nbDewasa.setMaxValue(7);
                nbDewasa.setWrapSelectorWheel(false);
                nbDewasa.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        //Toast.makeText(Pesawat.this, ""+picker.getValue(), Toast.LENGTH_SHORT).show();
                        valueNpDesawa = picker.getValue();
                    }
                });

                //number picker bayi
                nbBayi.setMinValue(0);
                nbBayi.setMaxValue(7);
                nbBayi.setWrapSelectorWheel(false);
                nbBayi.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        //Toast.makeText(Pesawat.this, ""+picker.getValue(), Toast.LENGTH_SHORT).show();
                        valueNpBayi = picker.getValue();
                    }
                });

                btnSelesai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        txtPenumpang.setText(valueNpDesawa + " Dewasa, " + valueNpBayi + " Bayi");
                    }
                });
                break;
            case R.id.btn_cari:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_BERANGKAT) {
            //memastikan jika di benar2 add dengan menangkan resulcode jika sama dengan RESULT_ADD
            //eksekusi
            if (resultCode == RESULT_BERANGKAT) {
                kereta = data.getParcelableExtra(VALUE_BERANGKAT);
                txtDari.setText(kereta.getNama());
            }
        } else if (requestCode == REQUEST_PULANG) {
            //memastikan jika di benar2 add dengan menangkan resulcode jika sama dengan RESULT_ADD
            //eksekusi
            if (resultCode == RESULT_PULANG) {
                kereta = data.getParcelableExtra(VALUE_PULANG);
                txtKe.setText(kereta.getNama());
            }
        }
    }

    @OnClick(R.id.btn_swap)
    public void onViewClicked() {
        if (swap) {
            String dari, ke;
            dari = txtDari.getText().toString();
            ke = txtKe.getText().toString();
            txtDari.setText(ke);
            txtKe.setText(dari);
            swap = false;
        } else {
            String dari, ke;
            dari = txtDari.getText().toString();
            ke = txtKe.getText().toString();
            txtDari.setText(ke);
            txtKe.setText(dari);
            swap = true;
        }
    }
}
