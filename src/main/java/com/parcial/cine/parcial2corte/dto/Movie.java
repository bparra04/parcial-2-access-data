/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.dto;

import com.parcial.cine.parcial2corte.dto.enums.MovieClasification;

/**
 *
 * @author bparra
 */
public class Movie {
    private String name;
    private String code;
    private MovieClasification clasification;

    public static Movie create(String name, String code, MovieClasification clasification) {
        return new Movie(name, code, clasification);
    }

    public Movie(String name, String code, MovieClasification clasification) {
        this.name = name;
        this.code = code;
        this.clasification = clasification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MovieClasification getClasification() {
        return clasification;
    }

    public void setClasification(MovieClasification clasification) {
        this.clasification = clasification;
    }
    
}
