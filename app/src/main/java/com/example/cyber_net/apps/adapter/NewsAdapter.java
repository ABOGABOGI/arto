package com.example.cyber_net.apps.adapter;

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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context mContext;
    List<News> mList;

    public NewsAdapter(Context mContext, List<News> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtJudul.setText(mList.get(position).getJudul());
        holder.txtIsi.setText(mList.get(position).getIsi());
        Glide.with(mContext)
                .load(mList.get(position).getUrl())
                .into(holder.immgNews);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.immg_news)
        ImageView immgNews;
        @BindView(R.id.txt_judul)
        TextView txtJudul;
        @BindView(R.id.txt_isi)
        TextView txtIsi;
        @BindView(R.id.btn_baca)
        Button btnBaca;
        @BindView(R.id.cv)
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
