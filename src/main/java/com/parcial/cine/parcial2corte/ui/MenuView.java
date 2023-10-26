package com.parcial.cine.parcial2corte.ui;

import com.parcial.cine.parcial2corte.dto.Cinema;
import com.parcial.cine.parcial2corte.dto.User;
import com.parcial.cine.parcial2corte.dto.enums.Role;
import com.parcial.cine.parcial2corte.services.AdminService;
import com.parcial.cine.parcial2corte.services.CinemaService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends javax.swing.JFrame {

    public MenuView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Menú del Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250); // Ajusté la altura para dar espacio a los nuevos botones
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelIzquierdo = new JPanel();
        JPanel panelDerecho = new JPanel(new GridLayout(6, 1, 0, 10)); // 6 filas, 1 columna, espacio vertical de 10 píxeles entre componentes

        JLabel etiquetaAdministrador = new JLabel("Administrador");
        JButton btnCrearAdmin = new JButton("Crear Administrador");
        JButton btnListarAdmins = new JButton("Listar Administradores");
        JButton btnCrearSala = new JButton("Crear Sala");
        JButton btnCrearFuncion = new JButton("Crear Función");
        JButton btnCrearPelicula = new JButton("Crear Película");
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        panelIzquierdo.add(etiquetaAdministrador);

        btnCrearAdmin.addActionListener((ActionEvent e) -> {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 2));
            
            JTextField nombreField = new JTextField();
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JTextField correoField = new JTextField();
            
            panel.add(new JLabel("Nombre:"));
            panel.add(nombreField);
            panel.add(new JLabel("Nombre de Usuario:"));
            panel.add(usernameField);
            panel.add(new JLabel("Contraseña:"));
            panel.add(passwordField);
            panel.add(new JLabel("Correo:"));
            panel.add(correoField);
            
            int option = JOptionPane.showConfirmDialog(null, panel, "Datos del Administrador",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (option == JOptionPane.OK_OPTION) {
                String nombre = nombreField.getText();
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                String correo = correoField.getText();
                
                User nuevoAdmin = new User(nombre, username, password, correo, Role.ADMIN);
                
                // Llamar a AdminService para intentar crear el nuevo administrador
                AdminService adminService = new AdminService();
                boolean adminCreado = adminService.createAdmin(nuevoAdmin);
                
                if (adminCreado) {
                    JOptionPane.showMessageDialog(null, "Administrador creado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el administrador", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnCrearSala.addActionListener((ActionEvent e) -> {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 2)); // 3 filas, 2 columnas
            
            JTextField nombreField = new JTextField();
            JTextField codigoField = new JTextField();
            JTextField capacidadField = new JTextField();
            
            panel.add(new JLabel("Nombre:"));
            panel.add(nombreField);
            panel.add(new JLabel("Código:"));
            panel.add(codigoField);
            panel.add(new JLabel("Capacidad:"));
            panel.add(capacidadField);
            
            int option = JOptionPane.showConfirmDialog(null, panel, "Datos de la Sala",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (option == JOptionPane.OK_OPTION) {
                
                String nombre = nombreField.getText();
                String codigo = codigoField.getText();
                int capacidad = Integer.parseInt(capacidadField.getText());
                
                Cinema nuevaSala = new Cinema(nombre, codigo, capacidad);
                
                CinemaService cinemaService = new CinemaService();
                boolean salaCreada = cinemaService.createCinema(nuevaSala);
                
                if (salaCreada) {
                    JOptionPane.showMessageDialog(null, "Sala creada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear la sala", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnListarAdmins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminService adminService = new AdminService();
                var admins = adminService.listUsersAdmin();

                // Puedes mostrar los administradores en un cuadro de diálogo o en una nueva ventana
                StringBuilder adminList = new StringBuilder("Lista de Administradores:\n");
                for (User admin : admins) {
                    adminList.append("Nombre: ").append(admin.getName())
                            .append(", Username: ").append(admin.getUsername())
                            .append(", Correo: ").append(admin.getEmail())
                            .append("\n");
                }

                JOptionPane.showMessageDialog(null, adminList.toString(), "Administradores", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
                loginView.setLocationRelativeTo(null);
                loginView.setVisible(true);
            }
        });

        panelDerecho.add(btnCrearAdmin);
        panelDerecho.add(btnListarAdmins);
        panelDerecho.add(btnCrearSala);
        panelDerecho.add(btnCrearFuncion);
        panelDerecho.add(btnCrearPelicula);
        panelDerecho.add(btnCerrarSesion);

        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(panelDerecho, BorderLayout.CENTER);
        add(panelPrincipal);
    }

}
