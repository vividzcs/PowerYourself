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

/**
 * 注册完成的监听器
 * @author zengxiaogang
 */

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    /**
     * userService服务类注入进来
     */
    private final IUserService service;

    /**
     * mailSender注入
     */
    private final JavaMailSender mailSender;

    /**
     * 发件人邮箱
     */
    @Value("${spring.mail.username}")
    private String senderEmail;

    /**
     * app名字
     */
    @Value("${nwuer.app.brand}")
    private String brand;

    @Autowired
    public RegistrationListener(IUserService service, JavaMailSender mailSender) {
        this.service = service;
        this.mailSender = mailSender;
    }

    /**
     * 时间发生的时候，执行如下方法
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    /**
     * 真正处理事件的方法
     * @param event the event to respond to
     */
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        //拿到user
        User user = event.getUser();
        //随机生成token
        String token = UUID.randomUUID().toString();
        //利用token，调用userService，生成验证码
        service.createVerificationToken(user, token);

        //拿到用户的邮箱
        String recipientAddress = user.getEmail();

        //构造邮件
        String subject = "是您注册了" + brand + "吗？请确认您的邮箱";
        String confirmationUrl
                = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String message = "点击一下链接，即可激活您的账户。";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(senderEmail);
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + System.lineSeparator() + brand + confirmationUrl);

        //发送邮件
        mailSender.send(email);
    }
}
