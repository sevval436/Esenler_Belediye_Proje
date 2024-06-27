package com.esenler.tasktracking.service.impl;

import com.esenler.tasktracking.entity.Employee;
import com.esenler.tasktracking.entity.Task;
import com.esenler.tasktracking.repository.EmployeeRepository;
import com.esenler.tasktracking.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddWithNewEmployee() {
        Task task = new Task();
        Employee employee = new Employee();
        employee.setId(null);
        employee.setName("test name");
        task.setEmployee(employee);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        taskService.add(task);

        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testAddWithExistingEmployee() {
        Task task = new Task();
        Employee employee = new Employee();
        employee.setId(1L);
        task.setEmployee(employee);

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        taskService.add(task);

        verify(employeeRepository, times(2)).findById(anyLong());
        verify(employeeRepository, times(0)).save(any(Employee.class));
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testFindAllTask() {
        Task task1 = new Task();
        task1.setDescription("Task 1");

        Task task2 = new Task();
        task2.setDescription("Task 2");

        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.findAllTask();

        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getDescription());
        assertEquals("Task 2", result.get(1).getDescription());

        verify(taskRepository, times(1)).findAll();
    }
}
