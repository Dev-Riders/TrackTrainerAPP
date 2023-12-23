package dev.devriders.tracktrainer.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.models.Categoria;
import dev.devriders.tracktrainer.models.Ejercicio;
import dev.devriders.tracktrainer.utils.Constants;
import dev.devriders.tracktrainer.views.activities.ejercicio.EjercicioDetalleActivity;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder> {

    private Context context;
    private List<Ejercicio> ejercicios;

    private Categoria categorias;

    public EjercicioAdapter(Context context, List<Ejercicio> ejercicios) {
        this.context = context;
        this.ejercicios = ejercicios;
        //this.categorias = categorias;
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
        Ejercicio ejercicio = ejercicios.get(position);
        //Categoria categoria = new Categoria(ejercicio.getCategorias().getIdCategoria(), ejercicio.getCategorias().getNombre_categoria());
        holder.exerciseName.setText(ejercicio.getNombreEjercicio());

        if (ejercicio.getImagenEjercicio() != null && !ejercicio.getImagenEjercicio().isEmpty()) {
            String imageUrl = Constants.BASE_URL + "/" + ejercicio.getImagenEjercicio();

            Glide.with(context)
                    .load(imageUrl)
                    .into(holder.exerciseImage);
        } else {
            holder.exerciseImage.setImageResource(R.drawable.backgroundwelcome);  // Imagen por defecto
        }
        //holder.categoria.set

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EjercicioDetalleActivity.class);
            intent.putExtra("nombreEjercicio", ejercicio.getNombreEjercicio());
            intent.putExtra("idEjercicio", ejercicio.getIdEjercicio());
            intent.putExtra("categorias", ejercicio.getCategorias().getNombre_categoria());
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