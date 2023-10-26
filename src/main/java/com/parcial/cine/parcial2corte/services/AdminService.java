/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.services;

import com.parcial.cine.parcial2corte.dao.AdminDao;
import com.parcial.cine.parcial2corte.dto.User;
import com.parcial.cine.parcial2corte.repositories.AdminRepository;
import java.util.List;

/**
 *
 * @author bparra
 */
public class AdminService {
    private final AdminRepository repository;
    
    public AdminService() {
        this.repository = new AdminRepository();
    }
    
    public boolean validateLogin(String username, String password) {
        return repository.doLogin(username, password);
    }
    
    public boolean createAdmin(User dto) {
        int response = repository.save(AdminDao.createDaoFromDto(dto));
        return response > 0;
    }
    
    public List<User> listUsersAdmin() {
        return repository.find()
                .stream()
                .map(AdminDao::toDto)
                .toList();
    }
}
