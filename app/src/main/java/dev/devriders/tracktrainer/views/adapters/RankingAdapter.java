package dev.devriders.tracktrainer.views.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.models.Puntaje;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {
    private final List<Puntaje> listaPuntajes;

    public RankingAdapter(List<Puntaje> listaPuntajes) {
        this.listaPuntajes = listaPuntajes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Puntaje puntaje = listaPuntajes.get(position);
        if (puntaje.getAmigo().getAmigo() != null) {
            Log.d("RankingAdapter", "Usuario: " + puntaje.getAmigo().getAmigo().getNickname());
            holder.textViewNombreUsuario.setText(puntaje.getAmigo().getAmigo().getNickname());
        } else {
            Log.d("RankingAdapter", "Usuario desconocido para la posición: " + position);
            holder.textViewNombreUsuario.setText("Amigo desconocido");
        }
        holder.textViewPuntaje.setText(String.valueOf(puntaje.getPuntajeHistorico()));
    }

    @Override
    public int getItemCount() {
        return listaPuntajes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreUsuario, textViewPuntaje;

        ViewHolder(View itemView) {
            super(itemView);
            textViewNombreUsuario = itemView.findViewById(R.id.textViewNombreUsuario);
            textViewPuntaje = itemView.findViewById(R.id.textViewPuntaje);
        }
    }
}