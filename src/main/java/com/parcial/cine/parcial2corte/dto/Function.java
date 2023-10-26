/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.dto;

/**
 *
 * @author bparra
 */
public class Function {
    private String code;
    private String date;
    private String movieCode;
    private String cinemaCode;

    public Function(String code, String date, String movieCode, String cinemaCode) {
        this.code = code;
        this.date = date;
        this.movieCode = movieCode;
        this.cinemaCode = cinemaCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMovieCode() {
        return movieCode;
    }

    public void setMovieCode(String movieCode) {
        this.movieCode = movieCode;
    }

    public String getCinemaCode() {
        return cinemaCode;
    }

    public void setCinemaCode(String hallCode) {
        this.cinemaCode = hallCode;
    }
    
    
}
