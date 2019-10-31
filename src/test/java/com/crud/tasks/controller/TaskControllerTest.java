/**package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.fasade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @MockBean
    private TrelloFacade trelloFacade;

    @MockBean
    private TrelloService trelloService;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;


    @Test
    public void shouldFetchEmptyTaskList() throws Exception{
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
    public void shouldFetchSelectedTask() throws Exception{
        //Given
        TaskDto selectedTask = new TaskDto(10L, "Selected Task", "test");

        when(taskMapper.mapToTaskDto(dbService.getTask(10L).orElseThrow(TaskNotFoundException::new)))
                .thenReturn(selectedTask);

        //When&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10L)))
                .andExpect(jsonPath("$.title", is("Selected Task")))
                .andExpect(jsonPath("$.content", is("test"))
                );
    }

    /**@Test
    public void shouldUpdateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(15L, "Test Task", "test");

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(any(TaskDto.class)))))
                .thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When&Then

        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                //.accept(MediaType.APPLICATION_JSON)
                //.characterEncoding("UTF-8")
                .content(jsonContent)



    }


*/

