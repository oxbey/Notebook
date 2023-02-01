package com.example.Notebook.repositories;

import com.example.Notebook.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
