/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.services;

import com.parcial.cine.parcial2corte.dao.HallDao;
import com.parcial.cine.parcial2corte.dto.Hall;
import com.parcial.cine.parcial2corte.repositories.HallRepository;

/**
 *
 * @author bparra
 */
public class HallService {
    private final HallRepository repository;
    private final FunctionService functionService;
    
    public HallService() {
        this.repository = new HallRepository();
        this.functionService = new FunctionService();
    }
    
    public boolean validateHall(String code) {
        return repository.existHall(code);
    }
    
    public boolean createHall(Hall dto) {
        int response = repository.save(HallDao.createDaoFromEntity(dto));
        return response > 0;
    }
    
    public boolean deleteCinema(String code) {
        //Validar si la sala no esta asociada a una funcion
        if(functionService.findFunctionByCinemaCode(code)) return false;
        return repository.delete(code);
    }
}
