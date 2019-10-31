/**package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldGetAllTasks(){
        //Given
        Task taskOne = new Task(1, "task_one", "test_task");
        Task taskTwo = new Task (2, "task_two", "test_task");

        List<Task> tasks = new ArrayList<>();
        tasks.add(taskOne);
        tasks.add(taskTwo);

        when(taskRepository.findAll()).thenReturn(tasks);

        //When
        List<Task> allTasks = dbService.getAllTasks();

        //Then
        Assert.assertEquals(2, allTasks.size());
    }

    @Test
    public void shouldReturnNull(){
        //Given
        when(taskRepository.findById(3L)).thenReturn(null);

        //When
        Optional<Task> task = dbService.getTask(3L);

        //Then
        Assert.assertEquals(null, task);
    }

    @Test
    public void shouldCreateTask(){
        //Given
        Task singleTask = new Task(20, "singleTask", "test task");

        //When
        dbService.saveTask(singleTask);

        //Then
        Assert.assertEquals(1, taskRepository.count());

    }

    @Test
    public void shoulDeleteTask(){
        //Given
        Task singleTask = new Task(20, "singleTask", "test task");
        taskRepository.save(singleTask);

        //When
        dbService.deleteTask(20L);

        //Then
        Assert.assertEquals(0, taskRepository.count());

    }

    }

*/
