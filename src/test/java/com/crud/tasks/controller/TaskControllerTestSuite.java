package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTestSuite {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @Test
    public void shouldGetSelectedTask() {
        //Given
        TaskDto taskOne = new TaskDto(1,"task_one", "test task");

        when(taskMapper.mapToTaskDto(dbService.getSingleTask(1L))).thenReturn(taskOne);

        //When
        TaskDto task = taskController.getTaskById(1L);

        //Then
        Assert.assertEquals("task_one", task.getTitle());
    }

    @Test
    public void shouldGetAllTasks(){
        //Given
        TaskDto taskOne = new TaskDto(1,"task_one", "test task");
        TaskDto taskTwo = new TaskDto(2, "task_two","test task");
        List<TaskDto> list = new ArrayList<>();
        list.add(taskOne);
        list.add(taskTwo);

        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(list);

        //When
        List<TaskDto> result = taskController.getTasks();

        //Then
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void shouldCreateTask(){
        //Given
        TaskDto taskOne = new TaskDto(1,"task_one", "test task");

        //When
        taskController.createTask(taskOne);

        //Then
        Assert.assertEquals(1,taskRepository.count());

    }

    @Test
    public void shouldDeleteTask(){
        //Given
        Task taskOne = new Task(1,"task_one", "test task");

        taskRepository.save(taskOne);

        //When
        taskController.deleteTask(1L);

        //Then
        Assert.assertEquals(0, taskRepository.count());
    }
}
