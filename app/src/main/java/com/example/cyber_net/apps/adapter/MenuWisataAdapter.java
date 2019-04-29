package com.example.cyber_net.apps.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.menu.menu2.Hotel;
import com.example.cyber_net.apps.menu.menu2.Kereta;
import com.example.cyber_net.apps.menu.menu2.Pelni;
import com.example.cyber_net.apps.menu.menu2.Pesawat;
import com.example.cyber_net.apps.menu.menu2.Wisata;
import com.example.cyber_net.apps.model.MenuModel;

import java.util.List;

public class MenuWisataAdapter extends RecyclerView.Adapter<MenuWisataAdapter.ViewHolder> {
    private Context context;
    private List<MenuModel> list;
    private OnSelect listener;
    SparseBooleanArray selecteditems;

    public MenuWisataAdapter(Context context, List<MenuModel> list) {
        this.context = context;
        this.list = list;
        selecteditems = new SparseBooleanArray();
    }

    public MenuWisataAdapter(Context context, List<MenuModel> list, OnSelect listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        selecteditems = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.ivGambarMenu.setImageResource(list.get(position).getIconMenu());
        holder.tvNamaMenu.setText(list.get(position).getNamaMenu());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        context.startActivity(new Intent(context, Pesawat.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, Kereta.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, Pelni.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, Hotel.class));
                        break;
                    case 4:
                        listener.onPulsa();
                        break;
                    case 5:
                        listener.onPpob();
                        break;
                    case 6:
                        context.startActivity(new Intent(context, Wisata.class));
                        break;
                    case 7:
                        listener.onBanking();
                        break;
                    default:
                        break;
                }

                holder.cv.setCardBackgroundColor(context.getResources().getColor(R.color.blue));
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        holder.cv.setCardBackgroundColor(context.getResources().getColor(R.color.colorWhite));

                        //jeda selesai Splashscreen
                        this.finish();
                    }

                    private void finish() {
                        // TODO Auto-generated method stub

                    }
                }, 500);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGambarMenu;
        TextView tvNamaMenu;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            ivGambarMenu = itemView.findViewById(R.id.img_menu);
            tvNamaMenu = itemView.findViewById(R.id.txt_menu);
            cv = itemView.findViewById(R.id.cv);
        }
    }

    public interface OnSelect {
        public void onPulsa();

        public void onPpob();

        public void onBanking();
    }
}
