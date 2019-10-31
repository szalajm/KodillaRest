package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {

    @Test
    public void noArgumentConstructorTest(){
        //Given
        Task task = new Task();

        //When
        String result = task.getContent();

        //Then
        Assert.assertEquals(null, result);
    }
}
