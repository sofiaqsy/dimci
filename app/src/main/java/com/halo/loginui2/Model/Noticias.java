package com.halo.loginui2.Model;

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




}
