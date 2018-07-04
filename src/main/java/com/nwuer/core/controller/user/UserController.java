package com.nwuer.core.controller.user;

import com.nwuer.core.common.Const;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.common.util.MD5;
import com.nwuer.core.common.util.RegisterCache;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.pojo.Role;
import com.nwuer.core.service.impl.SendEmailForUpdateService;
import com.nwuer.core.service.impl.UserServiceImpl;
import com.nwuer.core.vo.RegistrationFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author vividzc
 * @date 2018/6/23 13:28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SendEmailForUpdateService sendEmailForUpdateService;


    @RequestMapping("/")
    public String index() {
        return "user/index";
    }

    @RequestMapping(value = "/to_change_mes",method = RequestMethod.GET)
    public ModelAndView toChangePass(HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/change_mes");
        mv.addObject("userDto",userDto);
        return mv;
    }

    @RequestMapping(value = "/change_mes",method = RequestMethod.POST)
    public ModelAndView toChangePass(@Valid RegistrationFormVo registrationFormVo, Errors errors, HttpSession session) throws UnsupportedEncodingException, MessagingException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userDto",session.getAttribute(Const.CURRENT_USER));
        mv.setViewName("/user/change_mes");
        if(errors.hasErrors()){
            String errorMsg = errors.getAllErrors().get(0).getDefaultMessage();
            mv.addObject("msg",errorMsg);
            return mv;
        }
        UserDto userDto = (UserDto) session.getAttribute(Const.CURRENT_USER);
        registrationFormVo.setId(userDto.getId());
        registrationFormVo.setPassword(MD5.md5(registrationFormVo.getPassword()));

        boolean flag = false; // 是否需要发邮件
        ServerResponse res;
        if(userDto.getUsername().equals(registrationFormVo.getUsername())
                && userDto.getEmail().equals(registrationFormVo.getEmail())) {
            //都没改
            res = userService.updateByFormVo(registrationFormVo);
            if(res.isSuccess()){
                userDto = userService.selectById(userDto.getId());
                userDto.setPassword(null);
                session.setAttribute(Const.CURRENT_USER,userDto);
                mv.setViewName("/user/index");
            }else{
                //失败
                mv.addObject("msg",res.getMsg());
            }
        }else if(!userDto.getUsername().equals(registrationFormVo.getUsername())
                && userDto.getEmail().equals(registrationFormVo.getEmail())){ //用户名改了,email没改
            ServerResponse canUpdate = userService.usernameCanUpdate(userDto.getId(),registrationFormVo.getUsername());

            if(canUpdate.isSuccess()){
                res = userService.updateByFormVo(registrationFormVo);
                if(res.isSuccess()){
                    userDto = userService.selectById(userDto.getId());
                    userDto.setPassword(null);
                    session.setAttribute(Const.CURRENT_USER,userDto);
                    mv.setViewName("/user/index");
                }else {
                    mv.addObject("msg",res.getMsg());
                }
            }else{
                //失败
                mv.addObject("msg",canUpdate.getMsg());
            }

        } else if(userDto.getUsername().equals(registrationFormVo.getUsername())
                && !userDto.getEmail().equals(registrationFormVo.getEmail())) { //
            //直接发邮件
            ServerResponse canUpdate = userService.emailCanUpdate(userDto.getId(),registrationFormVo.getEmail());
            if(canUpdate.isSuccess()){
                flag = true;
            }else{
                //失败
                mv.addObject("msg",canUpdate.getMsg());
            }

        }else{
            //需要验证用户名并发邮件
            ServerResponse canUpdate = userService.usernameCanUpdate(userDto.getId(),registrationFormVo.getUsername());
            if(canUpdate.isSuccess()){
                flag = true;
            }else{
                //失败
                mv.addObject("msg",canUpdate.getMsg());
            }


        }
        //如果修改了username,则检查username是否重复
        //如果修改了邮箱,检查邮箱是否重复,在不重复的情况下参考注册,得确认邮箱是否有人用

        if(flag){ //需要发邮件
            UserDto userD = new UserDto(userDto.getId(),
                    registrationFormVo.getUsername(),
                    registrationFormVo.getPassword(),
                    registrationFormVo.getEmail(),
                    new Date(),
                    Role.ORDINARY.getLevel()
            );

            RegisterCache.setKey(userD.getId(),userDto);

            sendEmailForUpdateService.sendEmail(userD,Const.URL_PREFIX);

            userService.freezeUser(userDto.getId()); //冻结
            mv.setViewName("/success");
            mv.addObject("msg","由于修改了邮箱,请前往邮箱验证,否则下一次将不能登录");

        }
        return mv;
    }



}
