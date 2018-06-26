package com.nwuer.core.service.impl;

import com.nwuer.core.common.Const;
import com.nwuer.core.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * @author vividzc
 * @date 2018/6/25 19:25
 */
@Service
public class SendSimpleEmailService {
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
    public SendSimpleEmailService( JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    /**
     * 发送邮件
     */
    public void sendEmail(UserDto userDto,String url) throws MessagingException, UnsupportedEncodingException {
        String token = Const.TOKEN_PREFIX + userDto.getId();

        //构造邮件
        String subject = "您在" + brand + "重置密码,请继续操作!";
        String resetUrl
                = url + "/reset?token=" + token;
        String message = "<h2>点击链接,重置您的密码为:"+Const.INITAIL_PASSWORD+"</h2>";

        /////////////////////////
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        InternetAddress from = new InternetAddress();
        from.setAddress(senderEmail);
        from.setPersonal(brand, "UTF-8");

        helper.setFrom(from);
        helper.setTo(userDto.getEmail());
        helper.setSubject(subject);
        StringBuilder sb = new StringBuilder(message);
        sb.append("<br/>");
        sb.append(brand);
        sb.append(":<br/><a href='"+resetUrl+"' target='_blank'>" + "重置密码</a>");
        helper.setText(sb.toString(), true);

        //发送邮件
        mailSender.send(mimeMessage);
    }
}
