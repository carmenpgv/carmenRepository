package gui;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class Modelo {
    private String ip;
    private String user;
    private String password;
    private String adminPassword;

    private Connection conexion;

    public Modelo() {
        getPropValues();
    }

    public String getIp() {
        return ip;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/juegosdemesa", user, password);
        } catch (SQLException sqle) {
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306", user, password);
                PreparedStatement statement = null;

                String code = leerFichero();
                String[] query = code.split("--");
                for(String aQuery: query) {
                    statement = conexion.prepareStatement(aQuery);
                    statement.executeUpdate();
                }
                assert statement != null;
                statement.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String leerFichero() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("mibasejuegos_java.sql"))) {
            String linea;
            StringBuilder stringBuilder = new StringBuilder();
            while ((linea = reader.readLine()) != null) {
                stringBuilder.append(linea + " ");
            }
            return linea;
        }
    }

    void desconectar() {
        try {
            conexion.close();
            conexion = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void insertarDisenador(String nombre, String apellidos, LocalDate fechanacimiento, String pais) {
        String sentenciaSql = "INSERT INTO disenadores (nombre, apellidos, fechanacimiento, pais) VALUES (?,?,?,?)";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setDate(3, Date.valueOf(fechanacimiento));
            sentencia.setString(4, pais);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    void insertarEditorial(String editorial, String email, String telefono, String productos, String web) {
        String sentenciaSql = "INSERT INTO editoriales (editorial, email, telefono, productos, web) VALUES (?,?,?,?,?)";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, editorial);
            sentencia.setString(2, email);
            sentencia.setString(3, telefono);
            sentencia.setString(4, productos);
            sentencia.setString(5, web);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    void insertarJuego(String titulo, String isbn, int duracionMin, int duracionMax, int jugadoresMin, int jugadoresMax,
                       String editorial, String genero, String disenador, float precio, LocalDate fechaPublicacion) {
        String sentenciaSql = "INSERT INTO juegos (titulo, isbn, duracion, jugadores, " +
                "ideditorial, genero, iddisenador, precio, fechapublicacion) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement sentencia = null;

        int ideditorial = Integer.valueOf(editorial.split(" ")[0]);
        int iddisenador = Integer.valueOf(disenador.split(" ")[0]);
        String duracion = duracionMin + "-" + duracionMax;
        String jugadores;
        if(jugadoresMin == jugadoresMax) {
            jugadores = "" + jugadoresMin;
        } else {
            jugadores = jugadoresMin + "-" + jugadoresMax;
        }

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, titulo);
            sentencia.setString(2, isbn);
            sentencia.setString(3, duracion);
            sentencia.setString(4, jugadores);
            sentencia.setInt(5,Integer.valueOf(ideditorial));
            sentencia.setString(6, genero);
            sentencia.setInt(7, Integer.valueOf(iddisenador));
            sentencia.setFloat(8, Float.valueOf(precio));
            sentencia.setDate(9, Date.valueOf(fechaPublicacion));
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    void modificarEditorial(String editorial, String email, String telefono, String productos, String web,
                            int ideditorial) {
        String sentenciaSql = "UPDATE editoriales SET editorial = ?, email = ?, telefono = ?, productos = ?, web = ? " +
                "WHERE ideditorial = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, editorial);
            sentencia.setString(2, email);
            sentencia.setString(3, telefono);
            sentencia.setString(4, productos);
            sentencia.setString(5, web);
            sentencia.setInt(6, Integer.valueOf(ideditorial));
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void modificarDisenador(String nombre, String apellidos, LocalDate fechanacimiento, String pais, int iddisenador) {
        String sentenciaSql = "UPDATE disenadores SET nombre = ?, apellidos = ?, fechanacimiento = ?, pais = ?" +
                "WHERE iddisenador = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setDate(3, Date.valueOf(fechanacimiento));
            sentencia.setString(4, pais);
            sentencia.setInt(5, Integer.valueOf(iddisenador));
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void modificarJuego(String titulo, String isbn, int duracionMin, int duracionMax, int jugadoresMin, int jugadoresMax,
                        String editorial, String genero, String disenador, float precio, LocalDate fechaPublicacion,
                        int idjuego) {
        String sentenciaSql = "UPDATE juegos SET titulo = ?, isbn = ?, duracion = ?, jugadores = ?, ideditorial = ?, " +
                "genero = ?, iddisenador = ?, precio = ?, fechapublicacion = ? WHERE idjuego = ?";
        PreparedStatement sentencia = null;

        int ideditorial = Integer.valueOf(editorial.split(" ")[0]);
        int iddisenador = Integer.valueOf(disenador.split(" ")[0]);
        String duracion = duracionMin + "-" + duracionMax;
        String jugadores;
        if(jugadoresMin == jugadoresMax) {
            jugadores = "" + jugadoresMin;
        } else {
            jugadores = "" + jugadoresMin + "-" + jugadoresMax;
        }

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, titulo);
            sentencia.setString(2, isbn);
            sentencia.setString(3, duracion);
            sentencia.setString(4, jugadores);
            sentencia.setInt(5, Integer.valueOf(ideditorial));
            sentencia.setString(6, genero);
            sentencia.setInt(7, Integer.valueOf(iddisenador));
            sentencia.setFloat(8, Float.valueOf(precio));
            sentencia.setDate(9, Date.valueOf(fechaPublicacion));
            sentencia.setInt(10, Integer.valueOf(idjuego));
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void borrarEditorial(int ideditorial) {
        String sentenciaSql = "DELETE FROM juegos WHERE ideditorial = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, Integer.valueOf(ideditorial));
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        sentenciaSql = "DELETE FROM editoriales WHERE ideditorial = ?";
        sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, Integer.valueOf(ideditorial));
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void borrarDisenador(int iddisenador) {
        String sentenciaSql = "DELETE FROM juegos WHERE iddisenador = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, Integer.valueOf(iddisenador));
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        sentenciaSql = "DELETE FROM disenadores WHERE iddisenador = ?";
        sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, Integer.valueOf(iddisenador));
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void borrarJuego(int idjuego) {
        String sentenciaSql = "DELETE FROM juegos WHERE idjuego = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, Integer.valueOf(idjuego));
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    ResultSet consultarEditorial() throws SQLException {
        String sentenciaSql = "SELECT concat(ideditorial) as 'ID', concat(editorial) as 'Nombre editorial', " +
                "concat(email) as 'Email', concat(telefono) as 'Teléfono', concat(productos) as 'Productos', " +
                "concat(web) as 'Web' FROM editoriales";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    ResultSet consultarDisenador() throws SQLException {
        String sentenciaSql = "SELECT concat(iddisenador) as 'ID', concat(nombre) as 'Nombre', " +
                "concat(apellidos) as 'Apellidos', concat(fechanacimiento) as 'Fecha nacimiento', concat(pais) as 'País' " +
                "FROM disenadores";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    ResultSet consultarJuegos() throws SQLException {
        String sentenciaSql = "SELECT concat(j.idjuego) as 'ID', concat(j.titulo) as 'Título', concat(j.isbn) as 'ISBN', " +
                "concat(j.duracion) as 'Duracion', concat(j.jugadores) as 'Jugadores', concat(e.ideditorial, ' - ', " +
                "e.editorial) as 'Editorial', concat(j.genero) as 'Género', concat(d.iddisenador, ' - ', " +
                "d.apellidos, ', ', d.nombre) as 'Diseñador', concat(j.precio) as 'Precio', concat(j.fechapublicacion) " +
                "as 'Fecha publicación' FROM juegos as j inner join editoriales as e on e.ideditorial = j.ideditorial " +
                "inner join disenadores as d on d.iddisenador = j.iddisenador";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    private void getPropValues() {
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);
            ip = prop.getProperty("ip");
            user = prop.getProperty("user");
            password = prop.getProperty("pass");
            adminPassword = prop.getProperty("admin");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void setPropValues(String ip, String user, String pass, String adminPass) {
        try {
            Properties prop = new Properties();
            prop.setProperty("ip", ip);
            prop.setProperty("user", user);
            prop.setProperty("pass", pass);
            prop.setProperty("admin", adminPass);
            OutputStream out = null;
            out = new FileOutputStream("config.properties");
            prop.store(out, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ip = ip;
        this.user = user;
        this.password = pass;
        this.adminPassword = adminPass;
    }

    public boolean juegoIsbnYaExiste(String isbn) {
        String salesConsult = "SELECT existeIsbn(?)";
        PreparedStatement function;
        boolean isbnExists = false;
        try {
            function = conexion.prepareStatement(salesConsult);
            function.setString(1, isbn);
            ResultSet rs = function.executeQuery();
            rs.next();
            isbnExists = rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isbnExists;
    }

    public boolean editorialNombreYaExiste(String nombre) {
        String editorialNameConsult = "SELECT existeNombreEditorial(?)";
        PreparedStatement function;
        boolean nameExists = false;
        try {
            function = conexion.prepareStatement(editorialNameConsult);
            function.setString(1, nombre);
            ResultSet rs = function.executeQuery();
            rs.next();
            nameExists = rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameExists;
    }

    public boolean disenadorNombreYaExiste(String nombre, String apellidos) {
        String nombreCompleto = apellidos + ", " + nombre;
        String disenadorNameConsult = "SELECT existeNombreDisenador(?)";
        PreparedStatement function;
        boolean nameExists = false;
        try {
            function = conexion.prepareStatement(disenadorNameConsult);
            function.setString(1, nombreCompleto);
            ResultSet rs = function.executeQuery();
            rs.next();
            nameExists = rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameExists;
    }

    public int contarJuegos() {
        String contandoJuegos = "SELECT contarJuegos()";
        PreparedStatement function;
        int nJuegos = 0;
        try {
            function = conexion.prepareStatement(contandoJuegos);
            ResultSet rs = function.executeQuery();
            rs.next();
            nJuegos = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nJuegos;
    }

    public int contarDisenadores() {
        String contandoDisenadores = "SELECT contarDisenadores()";
        PreparedStatement function;
        int nDisenadores = 0;
        try {
            function = conexion.prepareStatement(contandoDisenadores);
            ResultSet rs = function.executeQuery();
            rs.next();
            nDisenadores = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nDisenadores;
    }

    public int contarEditoriales() {
        String contandoEditoriales = "SELECT contarEditoriales()";
        PreparedStatement function;
        int nEditoriales = 0;
        try {
            function = conexion.prepareStatement(contandoEditoriales);
            ResultSet rs = function.executeQuery();
            rs.next();
            nEditoriales = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nEditoriales;
    }
}
