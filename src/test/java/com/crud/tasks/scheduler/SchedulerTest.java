/**package com.crud.tasks.scheduler;

import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testSender() {
        //Given
        when(taskRepository.count()).thenReturn(1L);

        //When


        //Then
        emailScheduler.sendInformationEmail();

    }
}
 */
