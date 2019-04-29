package com.example.cyber_net.apps.fragment.transaksi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.adapter.transaksi.RiwayatTransAdapter;
import com.example.cyber_net.apps.adapter.transaksi.StatusTransAdapter;
import com.example.cyber_net.apps.model.Status;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatTransaksi extends Fragment {


    @BindView(R.id.rv_transaksi)
    RecyclerView rvTransaksi;
    Unbinder unbinder;

    public RiwayatTransaksi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaksi, container, false);
        unbinder = ButterKnife.bind(this, view);

        getTransaksi();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getTransaksi() {
        try {

            rvTransaksi.setLayoutManager(new LinearLayoutManager(getActivity()));
            ArrayList<Status> animalNames = new ArrayList<>();
            animalNames.add(new Status("2019/04/11", "Buka Tabungan Haji", "Sedang diproses"));
            animalNames.add(new Status("2019/03/18", "Buka Tabungan Gemilang", "Selesai"));

            // set up the RecyclerView
            RiwayatTransAdapter adapter = new RiwayatTransAdapter(getActivity(), animalNames);
            rvTransaksi.setAdapter(adapter);
        } catch (Exception e) {

        }
    }
}
