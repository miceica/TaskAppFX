package com.ceica.taskappfx.controller;


import com.ceica.taskappfx.models.User;

public class TaskController {
    private User userLogged;

    public boolean login(String username, String password){
        User user = new User();
        userLogged = user.login(username,password);

        if (userLogged!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean createUser(String username, String pass, int rol) {
        User user = new User();
        return user.insertar("(username,password,rol_id) values (?,?,?)",username,pass,rol);
    }

    public boolean editUserPassword(String username, String password) {
        User user = new User();
        return user.actualizar("password=? where username=?", password, username);
    }

    /*public boolean listUser(){

    }*/

    public boolean deleteUser(String username){
        User user = new User();
        return user.borrar("username=?", username);
    }
}
