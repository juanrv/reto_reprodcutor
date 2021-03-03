package com.example.reto1.ui.artistas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.reto1.R;
import com.example.reto1.activityCancion;
import com.example.reto1.adaptador.ArtistaAdapter;
import com.example.reto1.model.Artista;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistasFragment extends Fragment {

    @BindView(R.id.lista_artistas)
    ListView listViewArtista;
    private List<Artista> artistas;
    @BindView(R.id.ac_artistas)
    EditText editText;
    ArtistaAdapter artistaAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_artistas, container, false);
        ButterKnife.bind(this,root);
        cargarInformacion();
        artistaAdapter = new ArtistaAdapter(getContext(), artistas);
        listViewArtista.setAdapter(artistaAdapter);
        listViewArtista.setClickable(true);
        listViewArtista.setOnItemClickListener((parent, view, position, id) -> {
            Artista objetoSeleccionado = (Artista) listViewArtista.getItemAtPosition(position);
            goToActivity(view, objetoSeleccionado.getNombreArtista());
        });
        editTextWatcher();


        return root;
    }
    public void goToActivity(View view, String nombreArtista) {
        Intent intent = new Intent(getContext(), activityCancion.class);
        intent.putExtra("nombreArtista", nombreArtista);
        startActivity(intent);
    }

    private void editTextWatcher(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                artistaAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void cargarInformacion() {
        artistas = new ArrayList<>(5);
        artistas.add(new Artista(R.drawable.beatles, getString(R.string.beatles), getString(R.string.poprock)));
        artistas.add(new Artista(R.drawable.blackpinck,getString(R.string.blackpink), getString(R.string.kpop)));
        artistas.add(new Artista(R.drawable.bts,getString(R.string.bts), getString(R.string.kpop)));
        artistas.add(new Artista(R.drawable.katanazero,getString(R.string.katanazero), getString(R.string.electro)));
        artistas.add(new Artista(R.drawable.camilosesto,getString(R.string.camilosesto), getString(R.string.romantica)));



    }

}