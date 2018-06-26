package com.nwuer.core.controller.user;

import com.nwuer.core.common.Const;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.common.util.MD5;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.service.impl.UserServiceImpl;
import com.nwuer.core.vo.RegistrationFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author vividzc
 * @date 2018/6/23 13:28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
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
    public ModelAndView toChangePass(@Valid RegistrationFormVo registrationFormVo, Errors errors, HttpSession session){
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
        ServerResponse res = userService.updateByFormVo(registrationFormVo);

        if(res.isSuccess()){
            userDto = userService.selectById(userDto.getId());
            userDto.setPassword(null);
            session.setAttribute(Const.CURRENT_USER,userDto);
            mv.setViewName("/user/index");
        }else{
            //失败
            mv.addObject("msg",res.getMsg());
        }
        return mv;
    }



}
