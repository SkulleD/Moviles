package com.example.a3eva_ej4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pFavAdapter extends RecyclerView.Adapter<pFavAdapter.MyViewHolder> {
    ArrayList<Pelicula> peliculas;
    onItemClickListener pListener;

    public pFavAdapter(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        pListener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView pTitulo;

        public MyViewHolder(View itemView, final pFavAdapter.onItemClickListener listener) {
            super(itemView);
            this.pTitulo = itemView.findViewById(R.id.favTitulo);

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
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_favoritos, parent, false);
        pFavAdapter.MyViewHolder mvh = new pFavAdapter.MyViewHolder(elemento, pListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(pFavAdapter.MyViewHolder holder, int position) {
        Pelicula peli = this.peliculas.get(position);
        holder.pTitulo.setText("Título: " + peli.getTitulo());
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }
}
