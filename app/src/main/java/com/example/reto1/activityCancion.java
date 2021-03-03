package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reto1.model.Cancion;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class activityCancion extends AppCompatActivity {



    @BindView(R.id.txt_nombre_cancion)
    TextView tituloCancion;
    @BindView(R.id.play_pause)
    Button playPause;
    @BindView(R.id.img_cancion)
    ImageView imagenCancion;

    private MediaPlayer cancionPlayer;
    private List<Cancion> canciones;
    private String nombreArtista;
    private Cancion cancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancion);
        cargarInformacion();
        ButterKnife.bind(this);
        Intent intent = getIntent();
        nombreArtista = intent.getStringExtra("nombreArtista");
        cancion = buscarCancion(nombreArtista);
        tituloCancion.setText(cancion.getNombreCancion());
        imagenCancion.setImageResource(cancion.getImagenAlbum());
        cancionPlayer = MediaPlayer.create(this, cancion.getCancion());
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cancionPlayer.isPlaying()){
                    cancionPlayer.pause();
                    playPause.setBackgroundResource(R.drawable.ic_play_button);
                }
                else {
                    cancionPlayer.start();
                    playPause.setBackgroundResource(R.drawable.ic_pause);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        cancionPlayer.pause();
        super.onPause();
    }

    private Cancion buscarCancion(String nombreArtista) {
        Cancion cancionAReproducir = new Cancion(getString(R.string.dynamite), getString(R.string.bts),R.drawable.bts, R.raw.dynamite);
        for (int i = 0; i < canciones.size();i++){
            if(canciones.get(i).getNombreArtista().equalsIgnoreCase(nombreArtista)){
                cancionAReproducir = canciones.get(i);
            }
        }
        return cancionAReproducir;
    }

    private void cargarInformacion() {
        canciones = new ArrayList<>();
        canciones.add(new Cancion(getString(R.string.dynamite), getString(R.string.bts),R.drawable.bts, R.raw.dynamite));
        canciones.add(new Cancion(getString(R.string.hitTheFloor), getString(R.string.katanazero), R.drawable.katanazero, R.raw.hit_the_floor));
        canciones.add(new Cancion(getString(R.string.how_you_like_that), getString(R.string.blackpink), R.drawable.blackpinck, R.raw.blackpink));
        canciones.add(new Cancion(getString(R.string.el_amor_de_mi_vida), getString(R.string.camilosesto), R.drawable.camilosesto, R.raw.camilosesto));
        canciones.add(new Cancion(getString(R.string.dont_let_me_down), getString(R.string.beatles), R.drawable.beatles, R.raw.beatles));
    }

}