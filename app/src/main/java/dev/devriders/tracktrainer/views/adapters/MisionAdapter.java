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
import dev.devriders.tracktrainer.models.Ejercicio;
import dev.devriders.tracktrainer.models.Mision;
import dev.devriders.tracktrainer.views.activities.ejercicio.EjercicioDetalleActivity;
import dev.devriders.tracktrainer.views.activities.misiones.MisionesDetalleActivity;

public class MisionAdapter extends RecyclerView.Adapter<MisionAdapter.MisionViewHolder>{

    private Context context;
    private List<Mision> misiones;

    public MisionAdapter(Context context, List<Mision> misiones) {
        this.context = context;
        this.misiones = misiones;
    }

    @NonNull
    @Override
    public MisionAdapter.MisionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mision_card, parent, false);
        return new MisionAdapter.MisionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MisionAdapter.MisionViewHolder holder, int position) {
        Mision mision = misiones.get(position);
        holder.misionName.setText(mision.getNombreMision());
        holder.misionDescripcion.setText(mision.getDescripcionMision());
       /* holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MisionesDetalleActivity.class);
            intent.putExtra("nombreMision", mision.getNombreMision());
            intent.putExtra("descripcionMision", mision.getDescripcionMision());
            context.startActivity(intent);
        });*/
    }
    @Override
    public int getItemCount() {
        return misiones.size();
    }

    static class MisionViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView misionName;
        TextView misionDescripcion;

        public MisionViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView2);
            misionName = itemView.findViewById(R.id.misionName);
            misionDescripcion = itemView.findViewById(R.id.misionDescripcion);
        }
    }
}
