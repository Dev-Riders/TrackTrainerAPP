package dev.devriders.tracktrainer.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.views.activities.ejercicio.EjercicioDetalleActivity;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder> {

    private Context context;
    private List<String> ejercicios;

    public EjercicioAdapter(Context context, List<String> ejercicios) {
        this.context = context;
        this.ejercicios = ejercicios;
    }

    @NonNull
    @Override
    public EjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ejercicio_card, parent, false);
        return new EjercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EjercicioViewHolder holder, int position) {
        String ejercicio = ejercicios.get(position);
        holder.exerciseName.setText(ejercicio);
        holder.exerciseImage.setImageResource(R.drawable.backgroundwelcome);  // Imagen temporal
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EjercicioDetalleActivity.class);
            intent.putExtra("nombreEjercicio", ejercicio);  // Pasa el nombre del ejercicio a la actividad
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    static class EjercicioViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView exerciseName;
        ImageView exerciseImage;

        public EjercicioViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            exerciseName = itemView.findViewById(R.id.exerciseName);
            exerciseImage = itemView.findViewById(R.id.exerciseImage);
        }
    }
}
