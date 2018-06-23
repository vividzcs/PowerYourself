package com.nwuer.core.controller;

import com.nwuer.core.common.Const;
import com.nwuer.core.common.ResponseCode;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.common.event.OnRegistrationCompleteEvent;
import com.nwuer.core.common.util.ResultUtil;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.entity.User;
import com.nwuer.core.service.IUserService;
import com.nwuer.core.service.MailSendService;
import com.nwuer.core.service.impl.CategoryService;
import com.nwuer.core.vo.RegistrationFormVo;
import com.nwuer.core.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vividzc
 * @date 2018/6/13 16:52
 */
@Controller
public class IndexController {

    private final MailSendService mailSendService;
    private final IUserService userService;

    private final ApplicationEventPublisher eventPublisher;
    private final CategoryService categoryService;
    @Autowired
    public IndexController(ApplicationEventPublisher eventPublisher,
                           MailSendService mailSendService, IUserService userService,
                           CategoryService categoryService) {
        this.eventPublisher = eventPublisher;
        this.mailSendService = mailSendService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  ModelAndView index(HttpSession session){
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index");
        if(user != null){


            List<Object[]> listOrdered = categoryService.getCategoryOrdered(user.getId(),"0");
            for(int i=0; i<listOrdered.size();i++) {
                StringBuilder str = new StringBuilder();
                for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                    str.append("&nbsp;&nbsp;");
                }
                listOrdered.get(i)[1] = str;
            }

            mv.addObject("allCategoryOrdered",listOrdered);
        }

        return mv;
    }

    @RequestMapping(value = "/to_login",method = RequestMethod.GET)
    public  String toLogin(){
        return "login";
    }

    @RequestMapping(value = "to_register",method = RequestMethod.GET)
    public String toRegister(){
        return "register";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> login(UserDto userDto, HttpSession session){
        ServerResponse res = userService.login(userDto);
        if(res.isSuccess()) {
            userDto.setPassword(null);
            session.setAttribute(Const.CURRENT_USER,res.getData());
        }

        return res;

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
        //验证注册表单信息
        StringBuilder errorMessage = new StringBuilder();
        if (!errors.hasErrors()) {
            //注册表单没有错，创建新用户
            newAccount = userService.createNewAccount(new UserDto(null,
                    form.getUsername(),
                    form.getPassword(),
                    form.getEmail()
            ));
        } else {
            //注册表单填写有错
            List<ObjectError> allErrors = errors.getAllErrors();
            for (ObjectError error : allErrors) {
                errorMessage.append(error.getDefaultMessage()).append("|");
            }
            errorMessage.delete(errorMessage.lastIndexOf("|"), errorMessage.capacity());
        }

        //注册失败
        if (newAccount == null) {
            //返回错误原因及代码
            return ResultUtil.error(ResponseCode.REGISTRATION_FORM_FILL_IN_INCORRECT.getCode(),
                    ResponseCode.REGISTRATION_FORM_FILL_IN_INCORRECT.getDesc() + errorMessage.toString());
        }
        //注册成功

        try {
            //发布事件，发送激活邮件
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newAccount, appUrl));
        } catch (Exception me) {
            //出现异常返回错误信息
            return ResultUtil.error(ResponseCode.EMAIL_SEND_ERROR);
        }

        //发送邮件的事件发布成功，返回注册成功的提戳
        return ResultUtil.success(new UserDto(null,
                newAccount.getUsername(),
                "******",
                newAccount.getEmail()));
    }

    @RequestMapping("test")
    public ModelAndView test(){
        ServerResponse res = ServerResponse.createBySuccess();
        ModelAndView mv = new ModelAndView();
        mv.addObject(res);
        mv.setViewName("index");
        return mv;
    }

}
