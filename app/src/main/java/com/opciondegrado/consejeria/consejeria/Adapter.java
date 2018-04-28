package com.opciondegrado.consejeria.consejeria;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AuxiliosReciclerview>{
    Dialog dialog;
    List<Auxilio> auxilios;

    public Adapter(List<Auxilio> auxilios) {
        this.auxilios = auxilios;
    }
    @NonNull
    @Override
    public AuxiliosReciclerview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.row_recycler,parent,false);
        AuxiliosReciclerview holder = new AuxiliosReciclerview (v);
        dialog = new Dialog (parent.getContext ());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AuxiliosReciclerview holder, int position) {
        final Auxilio auxilio = auxilios.get (position);
        holder.textView.setText (auxilio.getTitulo ());
        holder.textView.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext (),Popup.class);
                intent.putExtra ("Auxilio", auxilio);
                intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext ().startActivity (intent);
            }
        });
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
