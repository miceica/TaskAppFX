package com.ceica.taskappfx.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Task extends ModeloBase {
    private int idtask;
    private String title;
    private String description;
    private LocalDate datetime;
    private LocalDate deadline;
    private boolean status;
    private User user;

    public Task() {
    }

    public int getIdtask() {
        return idtask;
    }

    public void setIdtask(int idtask) {
        this.idtask = idtask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*public static List<Task> getTask() {
        List<Task> taskList = new ArrayList<>();
        Connection conn = Conexion.conectar();
        String sql = "SELECT idtask,title,description,datetime,deadline,status,user_iduser FROM tareasapp.task";
        try {
            Statement stm = conn.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while (respuesta.next()) {
                Task task = new Task();
                task.setIdtask(respuesta.getInt("idtask"));
                task.setTitle(respuesta.getString("title"));
                task.setDescription(respuesta.getString("description"));
                task.setDatetime(respuesta.getLocalDate("datetime"));
                task.setDatetime(respuesta.getLocalDate("deadline"));
                task.setStatus(respuesta.getBoolean("status"));

                taskList.add(task);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return taskList;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
        return taskList;
    }*/

    @Override
    protected String getNombreTabla() {
        return "task";
    }

    @Override
    protected Object createObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public String toString() {
        return "Task{" +
                "idtask=" + idtask +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", datetime=" + datetime +
                ", deadline=" + deadline +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
