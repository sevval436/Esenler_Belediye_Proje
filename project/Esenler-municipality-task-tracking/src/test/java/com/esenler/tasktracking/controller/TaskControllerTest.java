package com.esenler.tasktracking.controller;

import com.esenler.tasktracking.entity.Task;
import com.esenler.tasktracking.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void testFindAll() throws Exception {
        Task task1 = new Task();
        task1.setDescription("Test Task 1");

        Task task2 = new Task();
        task2.setDescription("Test Task 2");

        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskService.findAllTask()).thenReturn(tasks);

        mockMvc.perform(get("/task")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Test Task 1"))
                .andExpect(jsonPath("$[1].description").value("Test Task 2"));

        verify(taskService, times(1)).findAllTask();
    }

    @Test
    public void testAdd() throws Exception {
        Task task = new Task();
        task.setDescription("New Task");

        mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"New Task\"}"))
                .andExpect(status().isOk());

        verify(taskService, times(1)).add(any(Task.class));
    }
}
