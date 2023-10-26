/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.dao;

import com.parcial.cine.parcial2corte.dto.Movie;
import com.parcial.cine.parcial2corte.dto.enums.MovieClasification;

/**
 *
 * @author bparra
 */
public class MovieDao {
    public static final String TABLE_NAME= "pelicula";
    private String nombre;
    private String codigo;
    private String clasificacion;

    public MovieDao(String nombre, String codigo, String clasificacion) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.clasificacion = clasificacion;
    }
    
    public Movie toDto() {
        return new Movie(
            this.nombre,
            this.codigo,
            MovieClasification.valueOf(this.clasificacion)
        );
    }
    
    public static MovieDao createDaoFromDto(Movie dto) {
        return new MovieDao(dto.getName(), dto.getCode(), dto.getClasification().VALUE);
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

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    
}
