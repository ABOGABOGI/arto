package com.example.cyber_net.apps.adapter.spiner;

/**
 * Created by Fajar on 4/16/2019.
 */
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cyber_net.apps.R;

import java.util.List;
public class SpinnerCustomAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final String [] Lket;
    private final String [] Lharga;
    private final int mResource;

    public SpinnerCustomAdapter(@NonNull Context context, @LayoutRes int resource,
                              @NonNull String[] Aket,@NonNull String[] Aharga) {
        super(context, resource, 0, Aket);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        Lket = Aket;
        Lharga = Aharga;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView keterangan = (TextView) view.findViewById(R.id.item_keterangan);
        TextView harga = (TextView) view.findViewById(R.id.item_harga);
        keterangan.setText("Ket :"+Lket[position]);
        harga.setText("Harga :"+Lharga[position]);
        return view;
    }
}
