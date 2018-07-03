package com.halo.loginui2.Model;

import java.io.File;
import java.io.Serializable;

public class Incident {


    private int id;
    private TipoIncidente type;
    private String datePublication;
    private String place;
    private String comment;
    private EstadoIncidente state;
    private Ciudadano citizen;
    private Empleado employe;
    private String imagen;

    public Incident() {
    }

    public Incident(TipoIncidente type, String datePublication, String place, String comment, EstadoIncidente state, Ciudadano citizen, Empleado employe,String imagen) {
        this.type = type;
        this.datePublication = datePublication;
        this.place = place;
        this.comment = comment;
        this.state = state;
        this.citizen = citizen;
        this.employe = employe;
        this.imagen = imagen;
    }

    public Incident(TipoIncidente type, String datePublication, String place, String comment, EstadoIncidente state) {
        this.type = type;
        this.datePublication = datePublication;
        this.place = place;
        this.comment = comment;
        this.state = state;
    }

    public Incident(int id, TipoIncidente type, String datePublication, String place, String comment, EstadoIncidente state, Ciudadano citizen, Empleado employe,String imagen) {
        this.id = id;
        this.type = type;
        this.datePublication = datePublication;
        this.place = place;
        this.comment = comment;
        this.state = state;
        this.citizen = citizen;
        this.employe = employe;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoIncidente getType() {
        return type;
    }

    public void setType(TipoIncidente type) {
        this.type = type;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public EstadoIncidente getState() {
        return state;
    }

    public void setState(EstadoIncidente state) {
        this.state = state;
    }

    public Ciudadano getCitizen() {
        return citizen;
    }

    public void setCitizen(Ciudadano citizen) {
        this.citizen = citizen;
    }

    public Empleado getEmploye() {
        return employe;
    }

    public void setEmploye(Empleado employe) {
        this.employe = employe;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
