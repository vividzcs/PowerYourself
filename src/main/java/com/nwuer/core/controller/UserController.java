package com.nwuer.core.controller;

import com.nwuer.core.common.ResponseCode;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.common.event.OnRegistrationCompleteEvent;
import com.nwuer.core.common.util.ResultUtil;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.pojo.User;
import com.nwuer.core.service.IUserService;
import com.nwuer.core.service.MailSendService;
import com.nwuer.core.vo.RegistrationFormVo;
import com.nwuer.core.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

/**
 * @author vividzc
 * @date 2018/6/13 16:52
 */
@RestController
public class UserController {

    private final MailSendService mailSendService;
    private final IUserService userService;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserController(ApplicationEventPublisher eventPublisher, MailSendService mailSendService, IUserService userService) {
        this.eventPublisher = eventPublisher;
        this.mailSendService = mailSendService;
        this.userService = userService;
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ServerResponse<String> login(UserDto userDto){
        return userService.login(userDto);
    }

    @RequestMapping("send")
    public String send(String email,String str1,String str2){
        mailSendService.sendEmail(email,str1,str2);
        return "success";
    }

    @PostMapping("register")
    public ResultVo register(@Valid RegistrationFormVo form,
                             Errors errors,
                             WebRequest request) {
        User newAccount = null;
        StringBuilder errorMessage = new StringBuilder();
        if (!errors.hasErrors()) {
            newAccount = userService.createNewAccount(new UserDto(
                    form.getUsername(),
                    form.getPassword(),
                    form.getEmail()
            ));
        } else {
            List<ObjectError> allErrors = errors.getAllErrors();
            for (ObjectError error : allErrors) {
                errorMessage.append(error.getDefaultMessage()).append("|");
            }
            errorMessage.delete(errorMessage.lastIndexOf("|"), errorMessage.capacity());
        }
        if (newAccount == null) {
            return ResultUtil.error(ResponseCode.REGISTRATION_FORM_FILL_IN_INCORRECT.getCode(),
                    ResponseCode.REGISTRATION_FORM_FILL_IN_INCORRECT.getDesc() + errorMessage.toString());
        }

        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newAccount, appUrl));
        } catch (Exception me) {
            return ResultUtil.error(ResponseCode.EMAIL_SEND_ERROR);
        }

        return ResultUtil.success(new UserDto(
                newAccount.getUsername(),
                "******",
                newAccount.getEmail()));
    }

}
