package com.example.cyber_net.apps.adapter.menu2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.model.Kereta;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeretaAdapter extends RecyclerView.Adapter<KeretaAdapter.ViewHolder> {
    Activity context;
    List<Kereta> list;
    OnFunction listener;

    public KeretaAdapter(Activity context, List<Kereta> list, OnFunction listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public void updateList(List<Kereta> keretaList){
        list = new ArrayList<>();
        list.addAll(keretaList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kereta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtKereta.setText(list.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_kereta)
        TextView txtKereta;

        public ViewHolder(final View itemView) {/**/
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   listener.onSelected(list.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnFunction {
        void onSelected(Kereta data);
    }
}
