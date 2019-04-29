package com.example.cyber_net.apps.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cyber_net.apps.fragment.transaksi.RiwayatTransaksi;
import com.example.cyber_net.apps.fragment.transaksi.StatusTransaksi;

/**
 * Created by Cyber_net on 24/01/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int numberTabs;

    public PagerAdapter(FragmentManager fm, int numberTabs) {
        super(fm);
        this.numberTabs = numberTabs;
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }


    //Mengembalikan Fragment yang terkait dengan posisi tertentu
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StatusTransaksi();
            case 1:
                return new RiwayatTransaksi();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberTabs;
    }
}
