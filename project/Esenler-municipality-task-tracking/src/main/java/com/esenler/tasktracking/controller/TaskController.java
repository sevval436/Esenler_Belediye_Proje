package com.esenler.tasktracking.controller;

import com.esenler.tasktracking.entity.Task;
import com.esenler.tasktracking.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task")
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> findAll() {
        return taskService.findAllTask();
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Task task) {
        taskService.add(task);
        return ResponseEntity.ok().build();
    }
}
