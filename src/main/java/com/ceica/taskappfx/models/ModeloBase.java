package com.ceica.taskappfx.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class ModeloBase {
    private static final String CONFIG_FILE = "config.properties";
    protected static String URL;
    protected static String USUARIO;
    protected static String PASSWORD;

    static {
        cargarConfiguracion();
    }

    private static void cargarConfiguracion() {
        Properties propiedades = new Properties();
        try (FileInputStream entrada = new FileInputStream(CONFIG_FILE)) {
            propiedades.load(entrada);
            URL = propiedades.getProperty("db.url");
            USUARIO = propiedades.getProperty("db.user");
            PASSWORD = propiedades.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método abstracto para obtener el nombre de la tabla
    protected abstract String getNombreTabla();

    // Métodos para CRUD
    public boolean insertar(String sql, Object... parametros) {
        sql = "INSERT INTO " + getNombreTabla() + " " + sql;
        return ejecutarQuery(sql, parametros);
    }

    public boolean actualizar(String sql, Object... parametros) {
        sql = "UPDATE " + getNombreTabla() + " set " + sql;
        return ejecutarQuery(sql, parametros);
    }

    public boolean borrar(String sql, Object... parametros) {
        sql = "DELETE FROM " + getNombreTabla() + " WHERE " + sql;
        return ejecutarQuery(sql, parametros);
    }

    //Metodo que devuelve la conexion a la bbdd
    public Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(URL,USUARIO,PASSWORD);
            return connection;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    // Método genérico para ejecutar consultas SQL
    private boolean ejecutarQuery(String sql, Object... parametros) {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            // Establecer los valores de los parámetros
            for (int i = 0; i < parametros.length; i++) {
                preparedStatement.setObject(i + 1, parametros[i]);
            }

            // Ejecutar la consulta
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Método genérico para ejecutar consultas SELECT
    protected List<Object> ejecutarQuerySelect(String sql, Object... parametros) {
        List<Object> result = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            // Establecer los valores de los parámetros
            for (int i = 0; i < parametros.length; i++) {
                preparedStatement.setObject(i + 1, parametros[i]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Object obj = createObjectFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     // Método para leer datos de la base de datos
    protected abstract Object createObjectFromResultSet(ResultSet resultSet) throws SQLException;

    protected List<Object> leerTodos() {
        List<Object> resultList = new ArrayList<>();

        String sql = "SELECT * FROM " + getNombreTabla();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement preparedStatement = conexion.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Object obj = createObjectFromResultSet(resultSet);
                resultList.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }


}
