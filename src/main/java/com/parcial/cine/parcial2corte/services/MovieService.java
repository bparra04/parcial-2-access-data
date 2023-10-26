/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.services;

import com.parcial.cine.parcial2corte.repositories.MovieRepository;

/**
 *
 * @author bparra
 */
public class MovieService {
    private final MovieRepository repository;
    private final FunctionService functionService;
    
    public MovieService() {
        this.repository = new MovieRepository();
        this.functionService = new FunctionService();
    }
    
    public boolean validateMovie(String code) {
        return repository.existMovie(code);
    }
    
    public boolean deleteMovie(String code) {
        //Validar si la pelicula no esta asociada a una funcion
        if(functionService.findFunctionByMovieCode(code)) return false;
        return repository.delete(code);
    }
    
}
