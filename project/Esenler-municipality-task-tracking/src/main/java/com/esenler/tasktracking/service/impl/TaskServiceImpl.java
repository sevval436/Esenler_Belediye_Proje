package com.esenler.tasktracking.service.impl;

import com.esenler.tasktracking.entity.Employee;
import com.esenler.tasktracking.entity.Task;
import com.esenler.tasktracking.repository.EmployeeRepository;
import com.esenler.tasktracking.repository.TaskRepository;
import com.esenler.tasktracking.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void add(Task task) {
        Employee employee = task.getEmployee();
        if (employee != null) {
            if (employee.getId() == null || employeeRepository.findById(employee.getId()).isEmpty()) {
               employee = employeeRepository.save(employee);
            } else {
                employee = employeeRepository.findById(employee.getId()).orElse(null);
            }
        }
        task.setEmployee(employee);
        taskRepository.save(task);
    }

    @Override
    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }
}
