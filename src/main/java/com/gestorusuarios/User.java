package com.gestorusuarios;

import java.util.Objects;

public class User {
    private String user;
    private String password;
    private String permisos;

    public User(String user, String password, String permisos) {
        this.user = user;
        this.password = password;
        this.permisos = permisos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return user.equals(user1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}