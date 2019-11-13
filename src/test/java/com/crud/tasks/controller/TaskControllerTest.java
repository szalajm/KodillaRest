package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.fasade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;


    @Test
    public void shouldFetchEmptyTaskList() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();

        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).
                thenReturn(taskDtoList);

        //When&Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0))
                );
    }

    @Test
    public void shouldFetchSelectedTask() throws Exception {
        //Given
        Task task = new Task(10L, "Selected Task", "test");
        TaskDto mappedTask = new TaskDto(10L, "Selected Task", "test");


        when(dbService.getTask(any())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).
                thenReturn(mappedTask);


        mockMvc.perform(get("/v1/tasks")
                .param("taskId", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is("Selected Task")))
                .andExpect(jsonPath("content", is("test"))
                );
    }


    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task task = new Task(25, "test task", "to be deleted");

        //When&Then

        mockMvc.perform(delete("/v1/tasks")
                .param("taskId", "25")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(70, "test task dto", "create task");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);


        //When&Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(70, "test task dto", "update task");
        Task mappedTAsk = new Task(70, "mapped task", "update task");

        when(taskMapper.mapToTask(taskDto)).thenReturn(mappedTAsk);
        when(dbService.saveTask(any())).thenReturn(mappedTAsk);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When&Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("test task dto")))
                .andExpect(jsonPath("$.content", is("update task")));
    }
}




