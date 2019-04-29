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
import com.example.cyber_net.apps.menu.menu2.detail.DetailPembiayaan;
import com.example.cyber_net.apps.model.IsiTabungan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.cyber_net.apps.helper.Constan.KEY_DATA;

public class PembiayaanAdapter extends RecyclerView.Adapter<PembiayaanAdapter.ViewHolder> {
    private Context context;
    private List<IsiTabungan> list;

    public PembiayaanAdapter(Context context, List<IsiTabungan> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tabungan, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.img.setImageResource(list.get(position).getImage());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        /*IsiTabungan data = list.get(position);
                        Intent intent = new Intent(context, DetailPembiayaan.class);
                        intent.putExtra(KEY_DATA, data);
                        context.startActivity(intent);*/
                        break;
                    case 1:
//                        context.startActivity(new Intent(context, Kereta.class));
                        break;
                    case 2:
//                        context.startActivity(new Intent(context, Pelni.class));
                        break;
                    case 3:
//                        context.startActivity(new Intent(context, Hotel.class));
                        break;
                    case 4:
//                        context.startActivity(new Intent(context, Pulsa.class));
                        break;
                    case 5:

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
        @BindView(R.id.img)
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}