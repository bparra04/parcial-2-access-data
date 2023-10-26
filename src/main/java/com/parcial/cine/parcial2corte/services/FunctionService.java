/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.services;

import com.parcial.cine.parcial2corte.dao.FunctionDao;
import com.parcial.cine.parcial2corte.dto.Function;
import com.parcial.cine.parcial2corte.repositories.FunctionRepository;

/**
 *
 * @author bparra
 */
public class FunctionService {
    private final FunctionRepository repository;
    private final MovieService movieService;
    private final CinemaService cinemaService;
    
    public FunctionService() {
        this.repository = new FunctionRepository();
        this.movieService = new MovieService();
        this.cinemaService = new CinemaService();
    }
    
    public boolean createFunction(Function dto) {
        //Validar codigo de pelicula
        if(!movieService.validateMovie(dto.getMovieCode())) return false;
        //Validar codigo de sala
        if(!cinemaService.validateCinema(dto.getCinemaCode())) return false;
        
        //Crear funcion
        int response = repository.save(FunctionDao.createDaoFromDto(dto));
        return response > 0;
    }
    
    public boolean findFunctionByMovieCode(String movieCode) {
        return repository.findByMovieCode(movieCode);
    }
    
    public boolean findFunctionByCinemaCode(String cinemaCode) {
        return repository.findByCinemaCode(cinemaCode);
    }
    
}
