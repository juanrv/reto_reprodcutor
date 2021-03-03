package com.example.reto1.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reto1.R;
import com.example.reto1.model.Artista;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistaAdapter extends BaseAdapter implements Filterable {

    private List<Artista> artistasEntrada;
    private List<Artista> artistasSalida;
    private final LayoutInflater inflater;


    public ArtistaAdapter(Context context, List<Artista> artistas) {
        this.artistasEntrada = artistas;
        this.artistasSalida = artistas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return artistasSalida.size();
    }

    @Override
    public Artista getItem(int position) {
        return artistasSalida.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if(view != null){
            viewHolder = (ViewHolder) view.getTag();
        }
        else {
            view = inflater.inflate(R.layout.item_artista, parent,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder.imagenArtista.setImageResource(artistasSalida.get(position).getImagenArtista());
        viewHolder.generoMusical.setText(artistasSalida.get(position).getGeneroMusical());
        viewHolder.nombreArtista.setText(artistasSalida.get(position).getNombreArtista());
        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Artista> listaFiltrada = new ArrayList<>();
                if(artistasEntrada == null){
                    artistasEntrada = new ArrayList<>(artistasSalida);
                }
                if(constraint == null){
                    results.count = artistasEntrada.size();
                    results.values = artistasEntrada;
                }
                else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < artistasEntrada.size(); i++) {
                        String data = artistasEntrada.get(i).getNombreArtista();
                        if(data.toLowerCase().contains(constraint.toString())){
                            listaFiltrada.add(artistasEntrada.get(i));
                        }
                    }
                    results.count = listaFiltrada.size();
                    results.values = listaFiltrada;
                }


                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                artistasSalida = (List<Artista>) results.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }

    class ViewHolder {
        @BindView(R.id.imagen_artista_cancion)
        ImageView imagenArtista;
        @BindView(R.id.nombre_artista)
        TextView nombreArtista;
        @BindView(R.id.genero_musical)
        TextView generoMusical;

        public ViewHolder (View view){
            ButterKnife.bind(this, view);
        }
    }



}
