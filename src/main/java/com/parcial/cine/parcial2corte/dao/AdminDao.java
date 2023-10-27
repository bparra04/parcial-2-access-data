/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.dao;

import com.parcial.cine.parcial2corte.dto.User;
import com.parcial.cine.parcial2corte.dto.enums.Role;
import java.sql.Connection;

public class AdminDao {

    public static final String TABLE_NAME = "administradores";
    private String nombre;
    private String username;
    private String password;
    private String correo;
    private Role role;

    public AdminDao(String nombre, String username, String password, String correo, Role role) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.role = role;
    }

    public User toDto() {
        return new User(
                this.nombre,
                this.username,
                this.password,
                this.correo,
                this.role
        );
    }

    public static AdminDao createDaoFromDto(User dto) {
        return new AdminDao(
                dto.getName(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getRole()
        );
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
