package com.example.Notebook.services;

import com.example.Notebook.exceptions.TaskNotFoundException;
import com.example.Notebook.model.Task;

import java.util.List;

public interface TaskService {

    Task save(Task task);
    List<Task> findAll();
    Task findById(Integer id) throws TaskNotFoundException;
}
