package com.nwuer.core.service;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vividzc
 * @date 2018/6/13 17:19
 */

@Slf4j
@Service
public class MailSendService {
    private static final String template = "mail.ftl";

    private final FreeMarkerConfigurer configurer;
    private final JavaMailSenderImpl javaMailSenderImpl;

    @Value("${nwuer.mail.subject}")
    private String subject;
    @Value("${nwuer.mail.personal}")
    private String personal;

    @Autowired
    public MailSendService(FreeMarkerConfigurer configurer, JavaMailSenderImpl javaMailSenderImpl) {
        this.configurer = configurer;
        this.javaMailSenderImpl = javaMailSenderImpl;
    }

    public void sendEmail(String to, String str1, String str2) {
        Map<String, String> map = new HashMap<>();
        map.put(str1, str1);
        map.put(str2, str2);
        try {
            String text = getTextByTemplate(template, map);
            send(to, text);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(new Date().toString(), e);
        }
    }

    private String getTextByTemplate(String template, Map<String, String> model) throws IOException, TemplateException {
        return FreeMarkerTemplateUtils.processTemplateIntoString(configurer.getConfiguration().getTemplate(template), model);
    }

    private void send(String to, String text) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        InternetAddress from = new InternetAddress();
        from.setAddress(javaMailSenderImpl.getUsername());
        from.setPersonal(personal, "UTF-8");

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        javaMailSenderImpl.send(mimeMessage);
    }


}
