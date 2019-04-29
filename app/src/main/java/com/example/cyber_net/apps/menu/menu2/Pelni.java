package com.example.cyber_net.apps.menu.menu2;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cyber_net.apps.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.cyber_net.apps.helper.ConvertDate.cekDate;
import static com.example.cyber_net.apps.helper.ConvertDate.tglHariIni;
import static com.example.cyber_net.apps.helper.ConvertDate.ubahTanggal2;

public class Pelni extends AppCompatActivity {

    @BindView(R.id.txt_dari)
    TextView txtDari;
    @BindView(R.id.ln_dari)
    LinearLayout lnDari;
    @BindView(R.id.btn_swap)
    FloatingActionButton btnSwap;
    @BindView(R.id.txt_ke)
    TextView txtKe;
    @BindView(R.id.ln_ke)
    LinearLayout lnKe;
    @BindView(R.id.txt_date_berangkat)
    TextView txtDateBerangkat;
    @BindView(R.id.opsi)
    Switch opsi;
    @BindView(R.id.ln_berangkat)
    LinearLayout lnBerangkat;
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
    private Calendar myCalendar;
    NumberPicker nPPria, nPWanita, npBayi;
    Button btnSelesai;
    int valueNpPria, valueNpWanita, valueNpBayi;
    boolean swap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelni);
        ButterKnife.bind(this);

        Typeface customfont= Typeface.createFromAsset(getAssets(),"fonts/roboto.ttf");
        txtDari.setTypeface(customfont);
        txtKe.setTypeface(customfont);
        txtDateBerangkat.setTypeface(customfont);
        txtDatePulang.setTypeface(customfont);
        txtPenumpang.setTypeface(customfont);
        btnCari.setTypeface(customfont);

        getSupportActionBar().setTitle("PELNI");
        getSupportActionBar().setSubtitle("Pesan Tiket PELNI");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swap = true;

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

    @OnClick({R.id.ln_dari, R.id.btn_swap, R.id.ln_ke, R.id.ln_berangkat, R.id.ln_pulang, R.id.ln_penumpang, R.id.btn_cari})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ln_dari:
                break;
            case R.id.btn_swap:
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
                break;
            case R.id.ln_ke:
                break;
            case R.id.ln_berangkat:
                new DatePickerDialog(Pelni.this, new DatePickerDialog.OnDateSetListener() {
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
            case R.id.ln_pulang:
                new DatePickerDialog(Pelni.this, new DatePickerDialog.OnDateSetListener() {
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
                                Pelni.this)){
                            return;
                        }else {
                            txtDatePulang.setText(ubahTanggal2(sdf.format(myCalendar.getTime())));
                        }
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.ln_penumpang:
                View view2 = getLayoutInflater().inflate(R.layout.select_penumpang_pelni, null);

                final BottomSheetDialog dialog = new BottomSheetDialog(Pelni.this);
                nPPria = view2.findViewById(R.id.nb_pria);
                nPWanita = view2.findViewById(R.id.nb_wanita);
                npBayi = view2.findViewById(R.id.nb_bayi);
                btnSelesai = view2.findViewById(R.id.btn_selesai);

                dialog.setContentView(view2);
                dialog.show();

                //number picker dewasa
                nPPria.setMinValue(0);
                nPPria.setMaxValue(7);
                nPPria.setWrapSelectorWheel(false);
                nPPria.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        //Toast.makeText(Pesawat.this, ""+picker.getValue(), Toast.LENGTH_SHORT).show();
                        valueNpPria = picker.getValue();
                    }
                });

                //number picker anak
                nPWanita.setMinValue(0);
                nPWanita.setMaxValue(7);
                nPWanita.setWrapSelectorWheel(false);
                nPWanita.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        //Toast.makeText(Pesawat.this, ""+picker.getValue(), Toast.LENGTH_SHORT).show();
                        valueNpWanita = picker.getValue();
                    }
                });

                //number picker bayi
                npBayi.setMinValue(0);
                npBayi.setMaxValue(7);
                npBayi.setWrapSelectorWheel(false);
                npBayi.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
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
                        txtPenumpang.setText(valueNpPria + " Pria, " + valueNpWanita + " Wanita, "+ valueNpBayi + " Bayi");
                    }
                });
                break;
            case R.id.btn_cari:
                break;
        }
    }
}
