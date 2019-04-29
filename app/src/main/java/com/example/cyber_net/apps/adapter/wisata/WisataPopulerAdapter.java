package com.example.cyber_net.apps.adapter.wisata;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.model.News;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WisataPopulerAdapter extends RecyclerView.Adapter<WisataPopulerAdapter.ViewHolder> {
    Context mContext;
    List<News> mList;

    public WisataPopulerAdapter(Context mContext, List<News> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wisata_pop, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtNama.setText(mList.get(position).getJudul());
        Glide.with(mContext)
                .load(mList.get(position).getUrl())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.bg)
        TextView bg;
        @BindView(R.id.txt_nama)
        TextView txtNama;
        @BindView(R.id.cv)
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}