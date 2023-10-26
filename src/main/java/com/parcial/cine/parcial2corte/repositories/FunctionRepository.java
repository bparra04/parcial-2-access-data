/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.repositories;

import com.parcial.cine.parcial2corte.config.SqlConnection;
import com.parcial.cine.parcial2corte.dao.FunctionDao;
import com.parcial.cine.parcial2corte.dto.Function;
import com.parcial.cine.parcial2corte.utils.ConnectionParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author bparra
 */
public class FunctionRepository implements BaseRepository<Function> {
    private Connection conn;
    
    @Override
    public int save(Function function) {
        int rest=0;
        try{
            String query = "INSERT INTO " + FunctionDao.TABLE_NAME + " (codigo_funcion, fecha, codigo_pelicula, codigo_sala) VALUES (?,?,?,?)";
            PreparedStatement ps = this.getConnection().prepareStatement(query);
            ps.setString(1, function.getCode());
            ps.setString(2, function.getDate());
            ps.setString(3, function.getMovieCode());
            ps.setString(4, function.getHallCode());
            rest = ps.executeUpdate();
            ps.close();
            this.getConnection().close();
        }catch(SQLException  e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rest;
    }

    @Override
    public boolean delete(String code) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Function> find() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Connection getConnection() {
        if (this.conn == null) {
            this.conn = SqlConnection.getConexion(
                ConnectionParam.url,
                ConnectionParam.user,
                ConnectionParam.password
            );
            return conn;
        }
        return this.conn;
    }      
}
