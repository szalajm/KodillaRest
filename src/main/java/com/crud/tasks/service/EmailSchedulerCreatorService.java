package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.scheduler.EmailScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class EmailSchedulerCreatorService {

    @Autowired
    AdminConfig adminConfig;

    @Autowired
    CompanyConfig companyConfig;

    @Autowired
    EmailScheduler emailScheduler;


    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildSchedulerMail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "See task repository");
        context.setVariable("welcome_message", "Hallo");
        context.setVariable("goodbye_message", "Bye");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("company_details", companyConfig.getCompanyName() + " " + companyConfig.getEmail()+ " " + companyConfig.getPhoneNumber());
        return templateEngine.process("mail/email-scheduler", context);
    }

}
