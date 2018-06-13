package com.nwuer.core.common.listener;

import com.nwuer.core.common.event.OnRegistrationCompleteEvent;
import com.nwuer.core.pojo.User;
import com.nwuer.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final IUserService service;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${nwuer.mail.personal}")
    private String brand;

    @Autowired
    public RegistrationListener(IUserService service, JavaMailSender mailSender) {
        this.service = service;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "是您注册了" + brand + "吗？请确认您的邮箱";
        String confirmationUrl
                = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String message = "点击一下链接，即可激活您的账户。";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(senderEmail);
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + System.lineSeparator() + brand + confirmationUrl);
        mailSender.send(email);
    }
}
