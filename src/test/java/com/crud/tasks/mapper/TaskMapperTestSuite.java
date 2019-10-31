package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTaskDto(){
        //Given
        Task task = new Task(1L,"task_one", "test task");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(1L, taskDto.getId());

    }

    @Test
    public void testMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "task_one", "test task");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals("task_one", taskDto.getTitle());
    }

    @Test
    public void testMapTOTaskDtoList(){
        //Given
        Task taskOne = new Task(1L,"task_one", "test task");
        Task taskTwo = new Task(2l, "task_two", "test task");
        List<Task> taskList = new ArrayList<>();
        taskList.add(taskOne);
        taskList.add(taskTwo);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals("task_two", taskDtoList.get(1).getTitle());
    }
}
