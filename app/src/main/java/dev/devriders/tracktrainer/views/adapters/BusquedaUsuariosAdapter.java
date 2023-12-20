package dev.devriders.tracktrainer.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.models.Usuario;

public class BusquedaUsuariosAdapter extends RecyclerView.Adapter<BusquedaUsuariosAdapter.ViewHolder> {

    private List<Usuario> listaUsuarios;
    private OnAgregarAmigoListener onAgregarAmigoListener;
    public BusquedaUsuariosAdapter(List<Usuario> listaUsuarios, OnAgregarAmigoListener onAgregarAmigoListener) {
        this.listaUsuarios = listaUsuarios;
        this.onAgregarAmigoListener = onAgregarAmigoListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario_busqueda, parent, false);
        return new ViewHolder(view, onAgregarAmigoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        holder.tvNombreUsuario.setText(usuario.getNombre() + " " + usuario.getApellido());
        holder.btnAgregarAmigo.setOnClickListener(v -> onAgregarAmigoListener.onAgregarAmigo(usuario.getId()));
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreUsuario;
        Button btnAgregarAmigo;

        public ViewHolder(View itemView, OnAgregarAmigoListener onAgregarAmigoListener) {
            super(itemView);
            tvNombreUsuario = itemView.findViewById(R.id.tvNombreUsuario);
            btnAgregarAmigo = itemView.findViewById(R.id.btnAgregarAmigo);
        }
    }

    public interface OnAgregarAmigoListener {
        void onAgregarAmigo(Long idUsuario);
    }
}