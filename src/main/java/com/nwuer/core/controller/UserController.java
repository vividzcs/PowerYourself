package com.nwuer.core.controller;

import com.nwuer.core.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vividzc
 * @date 2018/6/13 16:52
 */
@RestController
public class UserController {

    @Autowired
    private MailSendService mailSendService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(){
        return null;
    }

    @RequestMapping("send")
    public String send(String email,String str1,String str2){
        mailSendService.sendEmail(email,str1,str2);
        return "success";
    }
}
