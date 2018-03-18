package com.alfian.asynctask;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by M. Alfian Azhari on 3/17/2018.
 */


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private final List<Mahasiswa> items;

    public MenuAdapter(List<Mahasiswa> items) {
        this.items = items;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mhs, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nama;

        //membuat modifiyer
        public ViewHolder(View itemView) {
            super(itemView);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
        }

        //melakukan set/mengubah
        public void bind(final Mahasiswa item) {
            tv_nama.setText(item.getNama());
        }
    }
}
