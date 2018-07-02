package com.halo.loginui2.Model;

public class Incident {


    private int id;
    private String type;
    private String datePublication;
    private String place;
    private String comment;
    private String state;
    private Ciudadano citizen;
    private Empleado employe;

    public Incident() {
    }

    public Incident(String type, String datePublication, String place, String comment, String state, Ciudadano citizen, Empleado employe) {
        this.type = type;
        this.datePublication = datePublication;
        this.place = place;
        this.comment = comment;
        this.state = state;
        this.citizen = citizen;
        this.employe = employe;
    }

    public Incident(String type, String datePublication, String place, String comment, String state) {
        this.type = type;
        this.datePublication = datePublication;
        this.place = place;
        this.comment = comment;
        this.state = state;
    }

    public Incident(int id, String type, String datePublication, String place, String comment, String state, Ciudadano citizen, Empleado employe) {
        this.id = id;
        this.type = type;
        this.datePublication = datePublication;
        this.place = place;
        this.comment = comment;
        this.state = state;
        this.citizen = citizen;
        this.employe = employe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
}
