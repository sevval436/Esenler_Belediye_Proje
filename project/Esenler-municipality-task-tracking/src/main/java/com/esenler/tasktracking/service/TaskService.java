package com.esenler.tasktracking.service;

import com.esenler.tasktracking.entity.Task;

import java.util.List;

public interface TaskService {

    void add(Task task);

    List<Task> findAllTask();
}
