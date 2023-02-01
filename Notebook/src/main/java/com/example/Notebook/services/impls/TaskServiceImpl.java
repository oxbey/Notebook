package com.example.Notebook.services.impls;

import com.example.Notebook.exceptions.TaskNotFoundException;
import com.example.Notebook.model.Task;
import com.example.Notebook.repositories.TaskRepository;
import com.example.Notebook.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository repository;

    @Override
    public Task save(Task task) {
       return repository.save(task);
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        repository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task findById(Integer id) throws TaskNotFoundException {
        Optional<Task> optionalTask = repository.findById(id);
        if (!optionalTask.isEmpty()) {
            return optionalTask.get();
        }
        throw new TaskNotFoundException("Task with id " + id + " not found");
    }
}
