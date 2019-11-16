package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.EmailSchedulerCreatorService;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailSchedulerCreatorService emailSchedulerCreatorService;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day mail";

    //@Scheduled(cron = "0 0 10 * * *
    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        System.out.println("wysyłam maila");

        if (size != 1) {
            javaMailSender.send(createMimeMessage(new Mail(
                    adminConfig.getAdminMail(),
                    null,
                    SUBJECT,
                    "Currently in database you have: " + size + " tasks"
            )));
        } else {
            javaMailSender.send(createMimeMessage(new Mail(
                    adminConfig.getAdminMail(),
                    null,
                    SUBJECT,
                    "Currently in database you have: one task"
            )));
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(emailSchedulerCreatorService.buildSchedulerMail(mail.getMessage()), true);
        };
    }
}
