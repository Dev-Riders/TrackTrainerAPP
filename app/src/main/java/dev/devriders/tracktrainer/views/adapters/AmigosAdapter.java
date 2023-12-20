package dev.devriders.tracktrainer.views.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.api.AmigosApi;
import dev.devriders.tracktrainer.models.Amigo;
import dev.devriders.tracktrainer.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        holder.apellidoAmigo.setText(" "+amigo.getAmigo().getApellido());
        holder.nickNameAmigo.setText(" ("+amigo.getAmigo().getNickname()+")");
        Log.d("AmigosAdapter", "Amigo ID: " + amigo.getIdamigos());
        holder.menuButton.setOnClickListener(view -> mostrarMenu(view, listaAmigos.get(position), position));
    }

    private void mostrarMenu(View view, Amigo amigo, int position) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.menu_amigos); // Tu archivo de menú
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_eliminar_amigo) {
                eliminarAmigo(amigo, position); // Aquí pasas la posición también
                return true;
            }
            return false;
        });
        popupMenu.show();
    }


    private void eliminarAmigo(Amigo amigo, int position) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmigosApi amigosApi = retrofit.create(AmigosApi.class);
        Call<Void> call = amigosApi.deleteAmigoById(amigo.getIdamigos());
        Log.d("AmigosAdapter", "Amigo a eliminar: " + amigo.getIdamigos());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listaAmigos.remove(position);
                    notifyItemRemoved(position);
                } else {
                    // Manejo de error, respuesta no exitosa
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejo de error, fallo en la llamada a la API
            }
        });
    }



    @Override
    public int getItemCount() {
        return listaAmigos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreAmigo;
        TextView apellidoAmigo;
        TextView nickNameAmigo;
        ImageView menuButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreAmigo = itemView.findViewById(R.id.textViewNombreAmigo);
            apellidoAmigo = itemView.findViewById(R.id.textViewApellidoAmigo);
            nickNameAmigo = itemView.findViewById(R.id.textViewNickNameAmigo);
            menuButton = itemView.findViewById(R.id.menuButton);
        }
    }
}

