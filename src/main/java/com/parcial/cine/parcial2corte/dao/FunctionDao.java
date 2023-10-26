/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.dao;

import com.parcial.cine.parcial2corte.dto.Function;

/**
 *
 * @author bparra
 */
public class FunctionDao {
    public static final String TABLE_NAME= "funcion";
    private String codigo;
    private String fecha;
    private String codigoPelicula;
    private String codigoSala;

    public FunctionDao(String codigo, String fecha, String codigoPelicula, String codigoSala) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.codigoPelicula = codigoPelicula;
        this.codigoSala = codigoSala;
    }

    public Function toDto() {
        return new Function(
            codigo, 
            codigo, 
            codigoPelicula, 
            codigoSala
        );
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoPelicula() {
        return codigoPelicula;
    }

    public void setCodigoPelicula(String codigoPelicula) {
        this.codigoPelicula = codigoPelicula;
    }

    public String getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala;
    }
    
    
}
