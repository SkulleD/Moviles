package com.example.a3eva_ej4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pAdapter extends RecyclerView.Adapter<pAdapter.MyViewHolder> {
    ArrayList<Pelicula> peliculas;
    onItemClickListener pListener;

    public pAdapter(ArrayList<Pelicula> peliculas) {
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

        public MyViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            this.poster = itemView.findViewById(R.id.mListado);
            this.pCalificacion = itemView.findViewById(R.id.pCalificacion);
            this.pTitulo = itemView.findViewById(R.id.pTitulo);
            this.pDirector = itemView.findViewById(R.id.pDirector);

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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento, parent, false);
        MyViewHolder mvh = new MyViewHolder(elemento, pListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(pAdapter.MyViewHolder holder, int position) {
        Pelicula peli = this.peliculas.get(position);
        holder.poster.setImageResource(peli.getPortada());
        holder.pCalificacion.setImageResource(peli.getClasi());
        holder.pTitulo.setText(peli.getTitulo());
        holder.pDirector.setText(peli.getDirector());
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }
}
