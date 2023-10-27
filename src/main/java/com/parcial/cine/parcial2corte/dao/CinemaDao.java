/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.dao;

import com.parcial.cine.parcial2corte.dto.Cinema;

public class CinemaDao {

    public static final String TABLE_NAME = "sala";
    private String nombre;
    private String codigo;
    private int capacidad;

    public CinemaDao(String nombre, String codigo, int capacidad) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.capacidad = capacidad;
    }

    public Cinema toDto() {
        return new Cinema(this.nombre, this.codigo, this.capacidad);
    }

    public static CinemaDao createDaoFromDto(Cinema dto) {
        return new CinemaDao(dto.getName(), dto.getCode(), dto.getCapacity());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

}
