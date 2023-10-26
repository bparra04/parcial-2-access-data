/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.repositories;

import com.parcial.cine.parcial2corte.config.SqlConnection;
import com.parcial.cine.parcial2corte.dao.AdminDao;
import com.parcial.cine.parcial2corte.dto.enums.Role;
import com.parcial.cine.parcial2corte.utils.ConnectionParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author bparra
 */
public class AdminRepository implements BaseRepository<AdminDao>{
    private Connection conn;
    
    @Override
    public int save(AdminDao user) {
        int rest=0;
        try{
            String query = "INSERT INTO " + AdminDao.TABLE_NAME + " (nombre, username, password, correo, rol) VALUES (?, ?,?,?,?)";
            PreparedStatement ps = this.getConnection().prepareStatement(query);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getCorreo());
            ps.setString(5, user.getRole().toString());
            rest = ps.executeUpdate();
            ps.close();
            this.getConnection().close();
        }catch(SQLException  e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rest;
    }

    @Override
    public boolean delete(String username) {
        try{
            String query = "delete from " + AdminDao.TABLE_NAME + " where username = ?";
            PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
            preparedStmt.setString(1, username);
            preparedStmt.execute();

            this.getConnection().close();
            return true;
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    public List<AdminDao> find() {
        List<AdminDao> list = new ArrayList<>();
        try {
            String query = "select nombre, username, password, correo, rol from "+ AdminDao.TABLE_NAME;
            Statement stmt = this.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(
                    new AdminDao(
                            rs.getString("nombre"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("correo"),
                            Role.valueOf(rs.getString("rol"))
                    )
                );
            }
            this.getConnection().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list;
    }
    
    public boolean doLogin(String username, String password) {
        boolean flag = false;
        try {
            String query = "select * from "+ AdminDao.TABLE_NAME + " where username='"+ username + "' and password='"+ password+"'";
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
    public Connection getConnection() {
        if (this.conn == null) {
            return SqlConnection.getConexion(
                ConnectionParam.url,
                ConnectionParam.user,
                ConnectionParam.password
            );
        }
        return this.conn;
    }
    
}
