/**package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerTest {

    private static final String SUBJECT = "Tasks: Once a day mail" ;
    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void testSender() {
        //Given
        when(taskRepository.count()).thenReturn(1L);

        Mail mail = new Mail(
                adminConfig.getAdminMail(),
                null,
                SUBJECT,
                "Currently in database you have: one task");

        //When

        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mail);

    }
}

*/