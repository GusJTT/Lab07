package com.example.lab07;

import android.net.Uri;

import java.io.Serializable;

public class Cuadro implements Serializable {
    private String imagen;
    private String autor;
    private String tecnica;
    private String titulo;
    private String categoria;
    private String descripcion;
    private int anio;

    public Uri getImagen() {
        return Uri.parse(imagen);
    }

    public void setImagen(Uri imagen) {
        this.imagen = imagen.toString();
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
