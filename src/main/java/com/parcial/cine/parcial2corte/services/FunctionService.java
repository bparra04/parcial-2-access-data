/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.services;

import com.parcial.cine.parcial2corte.dao.FunctionDao;
import com.parcial.cine.parcial2corte.dto.Function;
import com.parcial.cine.parcial2corte.repositories.FunctionRepository;


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
        if (!movieService.validateMovie(dto.getMovieCode())) {
            return false;
        }
        // Validar codigo de sala
        if (!cinemaService.validateCinema(dto.getCinemaCode())) {
            return false;
        }

        //Crear funcion
        int response = repository.save(FunctionDao.createDaoFromDto(dto));
        return response > 0;
    }

    public boolean findFunctionByCode(String functionCode) {
        return repository.findByFunctionCode(functionCode);
    }

    public boolean deleteFunction(String functionCode) {
        // Verificar si la función existe antes de intentar eliminarla
        if (!findFunctionByCode(functionCode)) {
            return false; // La función no existe, no se puede eliminar
        }

        // Intentar eliminar la función
        boolean deleted = repository.delete(functionCode);

        // Si la función se eliminó correctamente, retornar verdadero
        return deleted;
    }

    //Validar si una pelicula tiene una funcion (2)
    public boolean findFunctionByMovieCode(String movieCode) {
        return repository.findByMovieCode(movieCode);
    }

    //Validar si una sala esta tiene una funcion (1)
    public boolean findFunctionByCinemaCode(String cinemaCode) {
        return repository.findByCinemaCode(cinemaCode);
    }

}
