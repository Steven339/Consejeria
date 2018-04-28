package com.opciondegrado.consejeria.consejeria;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AuxiliosReciclerview>{

    List<Auxilio> auxilios;

    public Adapter(List<Auxilio> auxilios) {
        this.auxilios = auxilios;
    }

    @NonNull
    @Override
    public AuxiliosReciclerview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_recycler,parent,false);
        AuxiliosReciclerview holder = new AuxiliosReciclerview (v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AuxiliosReciclerview holder, int position) {
        Auxilio auxilio = auxilios.get (position);
        holder.textView.setText (auxilio.getTitulo ());
    }

    @Override
    public int getItemCount() {
        return auxilios == null ? 0 : auxilios.size();
    }

    public static class AuxiliosReciclerview extends RecyclerView.ViewHolder{
        TextView textView;
        public AuxiliosReciclerview(View itemView) {
            super (itemView);
            textView = itemView.findViewById (R.id.titulo);
        }
    }
}
