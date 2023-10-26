/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.repositories;

import com.parcial.cine.parcial2corte.config.SqlConnection;
import com.parcial.cine.parcial2corte.dao.FunctionDao;
import com.parcial.cine.parcial2corte.utils.ConnectionParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author bparra
 */
public class FunctionRepository implements BaseRepository<FunctionDao> {
    private Connection conn;
    
    @Override
    public int save(FunctionDao function) {
        int rest=0;
        try{
            String query = "INSERT INTO " + FunctionDao.TABLE_NAME + " (codigo_funcion, fecha, codigo_pelicula, codigo_sala) VALUES (?,?,?,?)";
            PreparedStatement ps = this.getConnection().prepareStatement(query);
            ps.setString(1, function.getCodigo());
            ps.setString(2, function.getFecha());
            ps.setString(3, function.getCodigoPelicula());
            ps.setString(4, function.getCodigoSala());
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
    
    public boolean findByCinemaCode(String cinemaCode) {
        boolean flag = false;
        try {
            String query = "select codigo_funcion, fecha, codigo_pelicula, codigo_sala from "+ FunctionDao.TABLE_NAME +" where codigo_sala='" + cinemaCode + "'";
            Statement stmt = this.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) flag=true;
            this.getConnection().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return flag;
    }
    
    public boolean findByMovieCode(String movieCode) {
        boolean flag = false;
        try {
            String query = "select codigo_funcion, fecha, codigo_pelicula, codigo_sala from "+ FunctionDao.TABLE_NAME +" where codigo_pelicula='" + movieCode + "'";
            Statement stmt = this.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) flag=true;
            this.getConnection().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return flag;
    }
}
