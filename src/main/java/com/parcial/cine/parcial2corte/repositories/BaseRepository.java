/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.repositories;

import java.sql.Connection;

public interface BaseRepository<T> {

    Connection getConnection();

    int save(T t);

    boolean delete(String code);
}
