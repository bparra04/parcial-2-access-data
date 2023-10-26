/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.repositories;

import com.parcial.cine.parcial2corte.config.SqlConnection;
import com.parcial.cine.parcial2corte.dao.HallDao;
import com.parcial.cine.parcial2corte.dto.Hall;
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
public class HallRepository implements BaseRepository<Hall>{
    private Connection conn;

    @Override
    public int save(Hall hall) {
        int rest=0;
        try{
            String query = "INSERT INTO " + HallDao.TABLE_NAME + " (Nombre, Codigo, Capacidad) VALUES (?,?,?)";
            PreparedStatement ps = this.getConnection().prepareStatement(query);
            ps.setString(1, hall.getName());
            ps.setString(2, hall.getCode());
            ps.setInt(3, hall.getCapacity());
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
        try{
            String query = "delete from " + HallDao.TABLE_NAME + " where Codigo = ?";
            PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
            preparedStmt.setString(1, code);
            preparedStmt.execute();
            this.getConnection().close();
            return true;
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
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
    
    public boolean existHall(String hallCode) {
        boolean flag = false;
        try {
            String query = "select Nombre,Codigo,Capacidad from "+ HallDao.TABLE_NAME +" where Codigo='" + hallCode + "'";
            Statement stmt = this.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) flag=true;
            this.getConnection().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return flag;
    }
    
    @Override
    public List<Hall> find() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}