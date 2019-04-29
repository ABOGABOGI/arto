package com.example.cyber_net.apps.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.apps.Config;
import com.example.cyber_net.apps.MainActivity;
import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.login.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.contrains)
    ConstraintLayout contrains;
    Unbinder unbinder;
    @BindView(R.id.txt_notif)
    TextView txtNotif;
    @BindView(R.id.txt_nama)
    TextView txtNama;
    @BindView(R.id.txt_nominal)
    TextView txtNominal;
    @BindView(R.id.wrapper)
    LinearLayout wrapper;
    @BindView(R.id.ln_billing)
    LinearLayout lnBilling;
    @BindView(R.id.ln_bahasa)
    LinearLayout lnBahasa;
    @BindView(R.id.ln_pass)
    LinearLayout lnPass;
    @BindView(R.id.ln_pertanyaan)
    LinearLayout lnPertanyaan;
    @BindView(R.id.ln_logout)
    LinearLayout lnLogout;
//    @BindView(R.id.ln_signin)
//    LinearLayout lnSignin;
    @BindView(R.id.ln)
    LinearLayout ln;
    SharedPreferences sharedPreferences;

    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        sharedPreferences = this.getActivity().getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
        txtNama.setText(sharedPreferences.getString("nama","kosong"));
        txtNominal.setText(sharedPreferences.getString("saldo","0"));
        lnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().commit();
                Toast.makeText(getActivity(),"Berhasil Logout",Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
