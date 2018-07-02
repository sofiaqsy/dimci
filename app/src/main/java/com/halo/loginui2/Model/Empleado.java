package com.halo.loginui2.Model;

public class Empleado {
    private int id;
    private String name;
    private String password;
    private String area;

    public Empleado() {
    }

    public Empleado(String name, String password, String area) {
        this.name = name;
        this.password = password;
        this.area = area;
    }

    public Empleado(int id, String name, String password, String area) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
