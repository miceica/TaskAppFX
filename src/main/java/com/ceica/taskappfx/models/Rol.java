package com.ceica.taskappfx.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Rol extends ModeloBase {
    private int idrol;
    private String description;

    public Rol() {
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    protected String getNombreTabla() {
        return "rol";
    }


    @Override
    public String toString() {
        return "Rol{" +
                "idrol=" + idrol +
                ", description='" + description + '\'' +
                '}';
    }

    public List<Rol> getAll() {
        List<Rol> rolList = new ArrayList<>();
        Rol rol1 = new Rol();
        Connection conn = rol1.getConnection();
        String sql = "SELECT idrol,description FROM rol";
        try {
            Statement stm = conn.createStatement();
            ResultSet resultSet = stm.executeQuery(sql);
            while (resultSet.next()) {
                Rol rol = new Rol();
                rol.setIdrol(resultSet.getInt("idrol"));
                rol.setDescription(resultSet.getString("description"));
                rolList.add(rol);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rolList;
    }
}
