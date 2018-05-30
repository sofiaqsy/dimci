package com.halo.loginui2.Model;

import java.util.ArrayList;
import java.util.List;

public class Noticias {

    private Integer id;
    private String medio;
    private String titulo;
    private String fecha;

    public Integer getId() {
        return id;
    }

    public Noticias setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMedio() {
        return medio;
    }

    public Noticias setMedio(String medio) {
        this.medio = medio;
        return  this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Noticias setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public Noticias setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public Noticias(Integer id, String medio, String titulo, String fecha) {
        this.id = id;
        this.medio = medio;
        this.titulo = titulo;
        this.fecha = fecha;
    }

    public Noticias(){}

    private List<Noticias> noticiasList;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        noticiasList = new ArrayList<>();
        noticiasList.add(new Noticias(1,"El Comercio", "Noticia numero 1", "01/01/2018"));
        noticiasList.add(new Noticias(2,"Correo", "Noticia numero 2", "01/02/2018"));
        noticiasList.add(new Noticias(3,"Peru 21", "Noticia numero 3", "01/03/2018"));
        noticiasList.add(new Noticias(4,"Peru 21", "Noticia numero 3", "01/03/2018"));
        noticiasList.add(new Noticias(5,"Peru 21", "Noticia numero 3", "01/03/2018"));
    }



}
