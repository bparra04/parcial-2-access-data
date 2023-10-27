/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.services;

import com.parcial.cine.parcial2corte.dao.MovieDao;
import com.parcial.cine.parcial2corte.dto.Movie;
import com.parcial.cine.parcial2corte.repositories.MovieRepository;


public class MovieService {

    private final MovieRepository repository;

    public MovieService() {
        this.repository = new MovieRepository();
    }

    public boolean createMovie(Movie dto) {
        int response = repository.save(MovieDao.createDaoFromDto(dto));
        return response > 0;
    }

    public boolean validateMovie(String code) {
        return repository.existMovie(code);
    }

    //Borrar movie
    public boolean deleteMovie(String code) {
        return repository.delete(code);
    }

}
