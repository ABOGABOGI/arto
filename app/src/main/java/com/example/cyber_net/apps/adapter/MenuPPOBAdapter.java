package com.example.cyber_net.apps.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.menu.ppob.Asuransi;
import com.example.cyber_net.apps.menu.ppob.EMoney;
import com.example.cyber_net.apps.menu.ppob.Gas;
import com.example.cyber_net.apps.menu.ppob.Listrik;
import com.example.cyber_net.apps.menu.ppob.MULTIFINANCE;
import com.example.cyber_net.apps.menu.ppob.PDAM;
import com.example.cyber_net.apps.menu.ppob.Pajak;
import com.example.cyber_net.apps.menu.ppob.PascaBayar;
import com.example.cyber_net.apps.menu.ppob.TELKOM;
import com.example.cyber_net.apps.menu.ppob.TV;
import com.example.cyber_net.apps.model.MenuModel;

import java.util.List;

public class MenuPPOBAdapter extends RecyclerView.Adapter<MenuPPOBAdapter.ViewHolder> {
    private Context context;
    private List<MenuModel> list;
    private OnSelect listener;


    public MenuPPOBAdapter(Context context, List<MenuModel> list) {
        this.context = context;
        this.list = list;
    }

    public MenuPPOBAdapter(Context context, List<MenuModel> list, OnSelect listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu_pop_up, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ivGambarMenu.setImageResource(list.get(position).getIconMenu());
        holder.tvNamaMenu.setText(list.get(position).getNamaMenu());

        holder.ivGambarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        context.startActivity(new Intent(context, PascaBayar.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, Listrik.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, PDAM.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, TELKOM.class));
                        break;
                    case 4:
                        context.startActivity(new Intent(context, Gas.class));
                        break;
                    case 5:
                        context.startActivity(new Intent(context, TV.class));
                        break;
                    case 6:
                        context.startActivity(new Intent(context, Asuransi.class));
                        break;
                    case 7:
                        context.startActivity(new Intent(context, EMoney.class));
                        break;
                    case 8:
                        context.startActivity(new Intent(context, MULTIFINANCE.class));
                        break;
                    case 9:
                        context.startActivity(new Intent(context, Pajak.class));
                        break;
                    default:
                        break;
                }
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

        public ViewHolder(View itemView) {
            super(itemView);
            ivGambarMenu = itemView.findViewById(R.id.img_menu);
            tvNamaMenu = itemView.findViewById(R.id.txt_menu);
        }
    }

    public interface OnSelect {
        public void select();
    }
}
