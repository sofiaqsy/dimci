package com.halo.loginui2.Model;

import java.util.ArrayList;
import java.util.List;

public class Noticias {

private int id;
private String title;
private String description;
private String datePublication;


    public Noticias(int id, String title, String description, String datePublication) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.datePublication = datePublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }
}
