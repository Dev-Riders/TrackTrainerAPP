package dev.devriders.tracktrainer.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.models.Amigo;

public class AmigosAdapter extends RecyclerView.Adapter<AmigosAdapter.ViewHolder> {
    private List<Amigo> listaAmigos;

    public AmigosAdapter(List<Amigo> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    public void setAmigos(List<Amigo> listaAmigos) {
        this.listaAmigos = listaAmigos;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amigo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Amigo amigo = listaAmigos.get(position);
        holder.nombreAmigo.setText(amigo.getAmigo().getNombre());
    }

    @Override
    public int getItemCount() {
        return listaAmigos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreAmigo;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreAmigo = itemView.findViewById(R.id.textViewNombreAmigo);
        }
    }
}

