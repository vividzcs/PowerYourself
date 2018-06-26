package com.nwuer.core.common.listener;

import com.nwuer.core.common.Const;
import com.nwuer.core.common.event.OnRegistrationCompleteEvent;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

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
        try {
            confirmRegistration(event);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 真正处理事件的方法
     * @param event the event to respond to
     */
    private void confirmRegistration(OnRegistrationCompleteEvent event) throws MessagingException, UnsupportedEncodingException {
        //拿到user
        UserDto user = event.getUser();
        //拿到用户的邮箱
        String recipientAddress = user.getEmail();

        //构造邮件
        String subject = "是您注册了" + brand + "吗？请确认您的邮箱";
        String confirmationUrl
                = Const.URL_PREFIX + "/registrationConfirm/"+user.getId();

        String message = "<h2>点击链接,确认注册</h2><br/><p>如果被阻拦可以复制链接到新窗口打开</p><br/>";

        /////////////////////////
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        InternetAddress from = new InternetAddress();
        from.setAddress(senderEmail);
        from.setPersonal(brand, "UTF-8");

        helper.setFrom(from);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        StringBuilder sb = new StringBuilder(message);
        sb.append("<br/>");
        sb.append(brand);
        sb.append(":"+confirmationUrl);
        helper.setText(sb.toString(), true);

        //发送邮件
        mailSender.send(mimeMessage);
    }
}
