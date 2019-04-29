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
import android.widget.Toast;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.model.PPOB;

import java.util.List;
public class SpinnerListAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final int mResource;
    List<PPOB.Datum> list_ppob;

    public SpinnerListAdapter(@NonNull Context context, @LayoutRes int resource,
                                @NonNull List obList) {
        super(context, resource,0, obList);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        list_ppob = obList;
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

    private View createItemView(final int position, final View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView keterangan = (TextView) view.findViewById(R.id.one_item_keterangan);
        TextView id = (TextView) view.findViewById(R.id.one_item_id);
        keterangan.setText(list_ppob.get(position).getKeterangan());
        id.setText(list_ppob.get(position).getKode_ppob());
        return view;
    }
}
