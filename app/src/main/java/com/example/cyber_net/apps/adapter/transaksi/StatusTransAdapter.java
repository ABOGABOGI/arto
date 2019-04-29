package com.example.cyber_net.apps.adapter.transaksi;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.menu.menu2.detail.DetailPembiayaan;
import com.example.cyber_net.apps.model.IsiTabungan;
import com.example.cyber_net.apps.model.Status;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.cyber_net.apps.helper.Constan.KEY_DATA;

public class StatusTransAdapter extends RecyclerView.Adapter<StatusTransAdapter.ViewHolder> {
    private Context context;
    private List<Status> list;

    public StatusTransAdapter(Context context, List<Status> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transaksi, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txtTgl.setText(list.get(position).getTgl());
        holder.txtTranskasi.setText(list.get(position).getTransaksi());
        holder.txtStatus.setText(list.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_tgl)
        TextView txtTgl;
        @BindView(R.id.txt_transkasi)
        TextView txtTranskasi;
        @BindView(R.id.txt_status)
        TextView txtStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
