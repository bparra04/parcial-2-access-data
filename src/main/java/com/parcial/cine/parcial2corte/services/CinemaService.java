/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.services;

import com.parcial.cine.parcial2corte.dao.CinemaDao;
import com.parcial.cine.parcial2corte.dto.Cinema;
import com.parcial.cine.parcial2corte.repositories.CinemaRepository;

/**
 *
 * @author bparra
 */
public class CinemaService {
    private final CinemaRepository repository;
    private final FunctionService functionService;
    
    public CinemaService() {
        this.repository = new CinemaRepository();
        this.functionService = new FunctionService();
    }
    
    public boolean validateCinema(String code) {
        return repository.existHall(code);
    }
    
    public boolean createCinema(Cinema dto) {
        int response = repository.save(CinemaDao.createDaoFromDto(dto));
        return response > 0;
    }
    
    public boolean deleteCinema(String code) {
        //Validar si la sala no esta asociada a una funcion
        if(functionService.findFunctionByCinemaCode(code)) return false;
        return repository.delete(code);
    }
}
