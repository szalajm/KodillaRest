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

    @Autowired
    private TaskController taskController;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private TrelloFacade trelloFacade;

    @MockBean
    private TrelloService trelloService;

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
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0))
                );
    }

    @Test
    public void shouldFetchSelectedTask() throws Exception {
        //Given
        TaskDto selectedTask = new TaskDto(10L, "Selected Task", "test");
        taskRepository.save(taskMapper.mapToTask(selectedTask));

        when(taskMapper.mapToTaskDto(dbService.getTask(10L).orElseThrow(TaskNotFoundException::new))).
                thenReturn(selectedTask);

        //When&Then
        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Selected Task")))
                .andExpect(jsonPath("$[0].content", is("test"))
                );
    }

    @Test
    public void shouldDeleteTask(){
        //Given
        Task task = new Task (25, "test task", "to be deleted");

        //When
        taskController.deleteTask(25L);

        //Then
        verify(dbService, times(1)).deleteTask(25L);
    }

    @Test
    public void shouldCreateTask(){
        //Given
        TaskDto taskDto = new TaskDto(70, "test task dto", "create task");

        //When
        taskController.createTask(taskDto);

        //Then
        verify(dbService,times(1)).saveTask(taskMapper.mapToTask(taskDto));

    }

    @Test
    public void shouldUpdateTask(){
        //Given
        TaskDto taskDto = new TaskDto(70, "test task dto", "create task");

        //When
        taskController.updateTask(taskDto);

        //Then
        verify(taskMapper, times(1))
                .mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
    }
}

