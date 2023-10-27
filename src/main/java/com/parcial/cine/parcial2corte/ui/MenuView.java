package com.parcial.cine.parcial2corte.ui;

import com.parcial.cine.parcial2corte.dto.Cinema;
import com.parcial.cine.parcial2corte.dto.Function;
import com.parcial.cine.parcial2corte.dto.Movie;
import com.parcial.cine.parcial2corte.dto.User;
import com.parcial.cine.parcial2corte.dto.enums.MovieClasification;
import com.parcial.cine.parcial2corte.dto.enums.Role;
import com.parcial.cine.parcial2corte.services.AdminService;
import com.parcial.cine.parcial2corte.services.CinemaService;
import com.parcial.cine.parcial2corte.services.FunctionService;
import com.parcial.cine.parcial2corte.services.MovieService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
        JButton btnBorrarSala = new JButton("Borrar Sala");
        JButton btnCrearFuncion = new JButton("Crear Función");
        JButton btnBorrarFuncion = new JButton("Borrar Funcion");
        JButton btnCrearPelicula = new JButton("Crear Película");
        JButton btnBorrarPelicula = new JButton("Borrar Pelicula");
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        panelIzquierdo.add(etiquetaAdministrador);

        btnCrearAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                    Role role = Role.ADMIN;
                    User nuevoAdmin = new User(nombre, username, password, correo, role);

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
            }
        });

        btnCrearPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(4, 2)); // 4 filas, 2 columnas

                JTextField nombreField = new JTextField();
                JTextField codigoField = new JTextField();
                JComboBox<String> clasificacionCombo = new JComboBox<>(getClasificacionValues());

                panel.add(new JLabel("Nombre:"));
                panel.add(nombreField);
                panel.add(new JLabel("Código:"));
                panel.add(codigoField);
                panel.add(new JLabel("Clasificación:"));
                panel.add(clasificacionCombo);

                int option = JOptionPane.showConfirmDialog(null, panel, "Datos de la Película",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    String nombre = nombreField.getText();
                    String codigo = codigoField.getText();
                    String clasificacion = (String) clasificacionCombo.getSelectedItem();
                    MovieClasification clasificacionEnum = MovieClasification.valueOf(clasificacion);

                    Movie nuevaPelicula = new Movie(nombre, codigo, clasificacionEnum);

                    MovieService movieService = new MovieService();
                    boolean peliculaCreada = movieService.createMovie(nuevaPelicula);

                    if (peliculaCreada) {
                        JOptionPane.showMessageDialog(null, "Película creada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo crear la película", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Operación cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnBorrarPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoPelicula = JOptionPane.showInputDialog(null, "Ingrese el código de la película a borrar:");
                if (codigoPelicula != null && !codigoPelicula.isEmpty()) {
                    FunctionService functionService = new FunctionService();
                    if (functionService.findFunctionByMovieCode(codigoPelicula)) {
                        JOptionPane.showMessageDialog(null, "No se puede borrar la película. Tiene funciones asociadas.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        MovieService movieService = new MovieService();
                        boolean peliculaBorrada = movieService.deleteMovie(codigoPelicula);
                        if (peliculaBorrada) {
                            JOptionPane.showMessageDialog(null, "Película borrada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo borrar la película", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Código de película inválido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBorrarSala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoSala = JOptionPane.showInputDialog(null, "Ingrese el código de la sala a borrar:");
                if (codigoSala != null && !codigoSala.isEmpty()) {
                    FunctionService functionService = new FunctionService();
                    if (functionService.findFunctionByCinemaCode(codigoSala)) {
                        JOptionPane.showMessageDialog(null, "No se puede borrar la sala. Tiene funciones asociadas.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        CinemaService cinemaService = new CinemaService();
                        boolean salaBorrada = cinemaService.deleteCinema(codigoSala);
                        if (salaBorrada) {
                            JOptionPane.showMessageDialog(null, "Sala borrada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo borrar la sala", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Código de sala inválido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCrearSala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        btnCrearFuncion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(4, 2)); // 4 filas, 2 columnas

                JTextField codigoField = new JTextField();
                JTextField fechaField = new JTextField();
                JTextField codigoPeliculaField = new JTextField();
                JTextField codigoSalaField = new JTextField();

                panel.add(new JLabel("Código de Función:"));
                panel.add(codigoField);
                panel.add(new JLabel("Fecha (dd/mm/yyyy):"));
                panel.add(fechaField);
                panel.add(new JLabel("Código de Película:"));
                panel.add(codigoPeliculaField);
                panel.add(new JLabel("Código de Sala:"));
                panel.add(codigoSalaField);

                int option = JOptionPane.showConfirmDialog(null, panel, "Datos de la Función",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    String codigo = codigoField.getText();
                    String fecha = fechaField.getText();
                    String codigoPelicula = codigoPeliculaField.getText();
                    String codigoSala = codigoSalaField.getText();

                    MovieService movieService = new MovieService();
                    CinemaService cinemaService = new CinemaService();
                    boolean peliculaExiste = movieService.validateMovie(codigoPelicula);
                    boolean salaExiste = cinemaService.validateCinema(codigoSala);

                    if (peliculaExiste && salaExiste) {
                        Function nuevaFuncion = new Function(codigo, fecha, codigoPelicula, codigoSala);

                        FunctionService functionService = new FunctionService();
                        boolean funcionCreada = functionService.createFunction(nuevaFuncion);

                        if (funcionCreada) {
                            JOptionPane.showMessageDialog(null, "Función creada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo crear la función", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La película o la sala no existen", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Operación cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnListarAdmins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminService adminService = new AdminService();
                var admins = adminService.listUsersAdmin();

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

        btnBorrarFuncion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoFuncion = JOptionPane.showInputDialog(null, "Ingrese el código de la función a borrar:");
                if (codigoFuncion != null && !codigoFuncion.isEmpty()) {
                    FunctionService functionService = new FunctionService();
                    if (functionService.findFunctionByCode(codigoFuncion)) {
                        boolean funcionBorrada = functionService.deleteFunction(codigoFuncion);
                        if (funcionBorrada) {
                            JOptionPane.showMessageDialog(null, "Función borrada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo borrar la función", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ninguna función con ese código", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Código de función inválido", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
        panelDerecho.add(btnBorrarSala);
        panelDerecho.add(btnCrearFuncion);
        panelDerecho.add(btnBorrarFuncion);
        panelDerecho.add(btnCrearPelicula);
        panelDerecho.add(btnBorrarPelicula);
        panelDerecho.add(btnCerrarSesion);

        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(panelDerecho, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private String[] getClasificacionValues() {
        return Arrays.stream(MovieClasification.values())
                .map(Enum::name)
                .toArray(String[]::new);
    }

}
