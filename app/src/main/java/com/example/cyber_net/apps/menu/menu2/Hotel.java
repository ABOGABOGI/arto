package com.example.cyber_net.apps.menu.menu2;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cyber_net.apps.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.cyber_net.apps.helper.ConvertDate.cekDate;
import static com.example.cyber_net.apps.helper.ConvertDate.daysBetween;
import static com.example.cyber_net.apps.helper.ConvertDate.tglHariIni;
import static com.example.cyber_net.apps.helper.ConvertDate.ubahTanggal2;

public class Hotel extends AppCompatActivity {

    @BindView(R.id.txt_dari)
    TextView txtDari;
    @BindView(R.id.ln_hotel)
    LinearLayout lnHotel;
    @BindView(R.id.txt_date_in)
    TextView txtDateIn;
    @BindView(R.id.ln_in)
    LinearLayout lnIn;
    @BindView(R.id.txt_malam)
    TextView txtMalam;
    @BindView(R.id.txt_date_out)
    TextView txtDateOut;
    @BindView(R.id.ln_out)
    LinearLayout lnOut;
    @BindView(R.id.txt_penumpang)
    TextView txtPenumpang;
    @BindView(R.id.ln_kamar)
    LinearLayout lnKamar;
    @BindView(R.id.btn_cari)
    Button btnCari;
    private Calendar myCalendar;
    NumberPicker nPKamar, nPTamu;
    Button btnSelesai;
    int valueNpKamar, valueNpTamu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        ButterKnife.bind(this);

        Typeface customfont= Typeface.createFromAsset(getAssets(),"fonts/roboto.ttf");
        txtDari.setTypeface(customfont);
        txtDateIn.setTypeface(customfont);
        txtDateOut.setTypeface(customfont);
        txtMalam.setTypeface(customfont);
        txtPenumpang.setTypeface(customfont);
        btnCari.setTypeface(customfont);

        getSupportActionBar().setTitle("Hotel");
        getSupportActionBar().setSubtitle("Reservasi Hotel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myCalendar = Calendar.getInstance();
        txtDateIn.setText(ubahTanggal2(tglHariIni()));
        txtDateOut.setText(ubahTanggal2(tglHariIni()));
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

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.ln_hotel, R.id.ln_in, R.id.ln_out, R.id.ln_kamar, R.id.btn_cari})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ln_hotel:
                break;
            case R.id.ln_in:
                new DatePickerDialog(Hotel.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "yyyy-MM-dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        txtDateIn.setText(ubahTanggal2(sdf.format(myCalendar.getTime())));
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.ln_out:
                new DatePickerDialog(Hotel.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "yyyy-MM-dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);

                        //hari
                        String d1 = txtDateIn.getText().toString();
                        String d2 = ubahTanggal2(sdf.format(myCalendar.getTime()));

                        if (cekDate(d1, d2, "Tanggal check-out tidak boleh lebih kecil dari pada tanggal check-in",
                                Hotel.this)){
                            return;
                        }else {
                            txtDateOut.setText(ubahTanggal2(sdf.format(myCalendar.getTime())));
                            txtMalam.setText(""+daysBetween(d1, d2));
                        }
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.ln_kamar:
                View view2 = getLayoutInflater().inflate(R.layout.select_penumpang_hotel, null);

                final BottomSheetDialog dialog = new BottomSheetDialog(Hotel.this);
                nPKamar = view2.findViewById(R.id.nb_kamar);
                nPTamu = view2.findViewById(R.id.nb_tamu);
                btnSelesai = view2.findViewById(R.id.btn_selesai);

                dialog.setContentView(view2);
                dialog.show();

                //number picker dewasa
                nPKamar.setMinValue(0);
                nPKamar.setMaxValue(7);
                nPKamar.setWrapSelectorWheel(false);
                nPKamar.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        //Toast.makeText(Pesawat.this, ""+picker.getValue(), Toast.LENGTH_SHORT).show();
                        valueNpKamar = picker.getValue();
                    }
                });

                //number picker anak
                nPTamu.setMinValue(0);
                nPTamu.setMaxValue(7);
                nPTamu.setWrapSelectorWheel(false);
                nPTamu.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        //Toast.makeText(Pesawat.this, ""+picker.getValue(), Toast.LENGTH_SHORT).show();
                        valueNpTamu = picker.getValue();
                    }
                });

                btnSelesai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        txtPenumpang.setText(valueNpKamar + " Kamar, " + valueNpTamu + " Tamu");
                    }
                });
                break;
            case R.id.btn_cari:
                break;
        }
    }
}
