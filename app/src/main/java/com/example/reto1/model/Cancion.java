package com.example.reto1.model;

public class Cancion {
    private String nombreCancion;
    private String nombreArtista;
    private int imagenAlbum;
    private int cancion;

    public Cancion(String nombreCancion, String nombreArtista, int imagenAlbum, int cancion) {
        this.nombreCancion = nombreCancion;
        this.nombreArtista = nombreArtista;
        this.imagenAlbum = imagenAlbum;
        this.cancion = cancion;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public int getImagenAlbum() {
        return imagenAlbum;
    }

    public int getCancion() {
        return cancion;
    }
}
