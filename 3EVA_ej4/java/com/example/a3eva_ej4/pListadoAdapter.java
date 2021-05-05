package com.example.a3eva_ej4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class pListadoAdapter extends RecyclerView.Adapter<pListadoAdapter.MyViewHolder> {
    ArrayList<Pelicula> peliculas;
    private onItemClickListener pListener;

    public pListadoAdapter(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private ImageView pCalificacion;
        private TextView pTitulo;
        private TextView pDirector;
        private TextView pDuracion;
        private TextView pSala;
        private CalendarView pfecha;

        public MyViewHolder(View itemView, final pListadoAdapter.onItemClickListener listener) {
            super(itemView);
            this.poster = itemView.findViewById(R.id.mListado);
            this.pCalificacion = itemView.findViewById(R.id.pCalificacion);
            this.pTitulo = itemView.findViewById(R.id.pTitulo);
            this.pDirector = itemView.findViewById(R.id.pDirector);
            this.pDuracion = itemView.findViewById(R.id.pDuracion);
            this.pSala = itemView.findViewById(R.id.pSala);
            this.pfecha = itemView.findViewById(R.id.pFechaEstreno);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public pListadoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_listado, parent, false);
        pListadoAdapter.MyViewHolder mvh = new pListadoAdapter.MyViewHolder(elemento, pListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(pListadoAdapter.MyViewHolder holder, int position) {
        Pelicula peli = this.peliculas.get(position);
        holder.poster.setImageResource(peli.getPortada());
        holder.pCalificacion.setImageResource(peli.getClasi());
        holder.pTitulo.setText("Título: " + peli.getTitulo());
        holder.pDirector.setText("Director/a: " + peli.getDirector());
        holder.pDuracion.setText("Duración: " + peli.getDuracion());
        holder.pSala.setText("Duración: " + peli.getSala());
        //holder.pfecha.getDate(peli.getFecha());
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }
}
