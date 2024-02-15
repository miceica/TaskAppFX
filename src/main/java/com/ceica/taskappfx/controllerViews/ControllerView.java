package com.ceica.taskappfx.controllerViews;

import com.ceica.taskappfx.controller.TaskController;

public abstract class ControllerView {
    protected TaskController taskController;

    public void setTaskController(TaskController taskController) {
        this.taskController = taskController;
        cargaInicial();
    }

    public abstract void cargaInicial();
}
