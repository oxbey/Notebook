package com.example.Notebook.controllers;

import com.example.Notebook.exceptions.NotCorrectInputDataException;
import com.example.Notebook.exceptions.TaskNotFoundException;
import com.example.Notebook.model.Task;
import com.example.Notebook.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/todo")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final RestTemplate restTemplate;

    @GetMapping(path = "/all", produces = "application/json")
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @PostMapping(path = "/add/task", consumes = "application/json")
    public Task save(@Valid @RequestBody Task task, Errors errors) throws NotCorrectInputDataException {
        if (errors.hasErrors()) {
            throw new NotCorrectInputDataException(errors.getFieldError().getDefaultMessage());
        }
        return taskService.save(task);
    }

    @GetMapping("/task/{id}")
    public Task getById(@PathVariable Integer id) throws TaskNotFoundException {
        return taskService.findById(id);
    }

    @GetMapping(path = "/template", produces = "application/json")
    public Task[] getAllTasksByRestTemplate() {
        URI uri = URI.create("http://localhost:8080/todo/all");
        return restTemplate.getForObject(uri, Task[].class);
    }

    @AllArgsConstructor
    public static class TasksList implements Serializable {
        private final List<Task> tasks;
    }
}
