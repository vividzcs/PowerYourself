package com.nwuer.core.controller;

import com.github.pagehelper.PageInfo;
import com.nwuer.core.common.Const;
import com.nwuer.core.common.ResponseCode;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.common.event.OnRegistrationCompleteEvent;
import com.nwuer.core.common.util.MD5;
import com.nwuer.core.common.util.RegisterCache;
import com.nwuer.core.common.util.TokenCache;
import com.nwuer.core.common.util.UuidUtil;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.entity.User;
import com.nwuer.core.pojo.Role;
import com.nwuer.core.service.MailSendService;
import com.nwuer.core.service.impl.CategoryService;
import com.nwuer.core.service.impl.JobService;
import com.nwuer.core.service.impl.UserServiceImpl;
import com.nwuer.core.vo.JobListVo;
import com.nwuer.core.vo.LoginFormVo;
import com.nwuer.core.vo.RegistrationFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author vividzc
 * @date 2018/6/13 16:52
 */
@Controller
public class IndexController {

    private final MailSendService mailSendService;
    private final UserServiceImpl userService;

    private final ApplicationEventPublisher eventPublisher;
    private final CategoryService categoryService;
    private final JobService jobService;
    @Autowired
    public IndexController(ApplicationEventPublisher eventPublisher,
                           MailSendService mailSendService, UserServiceImpl userService,
                           CategoryService categoryService, JobService jobService) {
        this.eventPublisher = eventPublisher;
        this.mailSendService = mailSendService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.jobService = jobService;
    }



    @RequestMapping(value = "/to_login",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }
    @RequestMapping(value = "/to_forget",method = RequestMethod.GET)
    public String toForget(){
        return "forget";
    }

    @RequestMapping(value = "to_register",method = RequestMethod.GET)
    public String toRegister(){
        return "register";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> login(LoginFormVo loginFormVo, HttpSession session){
        ServerResponse res = userService.login(loginFormVo);
        if(res.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER,res.getData());
        }
        return res;

    }

//    @RequestMapping("send")
//    public String send(String email,String str1,String str2){
//        mailSendService.sendEmail(email,str1,str2);
//        return "success";
//    }

    @PostMapping("/register")
    @ResponseBody
    public ServerResponse register(@Valid RegistrationFormVo form,
                             Errors errors,
                             WebRequest request) {
        //验证注册表单信息
        StringBuilder errorMessage = new StringBuilder();
        if (!errors.hasErrors()) {
            //注册表单没有错，先将信息放到缓存中
            String uuid = UuidUtil.get32UUID();
            UserDto userDto = new UserDto(uuid,
                    form.getUsername(),
                    form.getPassword(),
                    form.getEmail(),
                    new Date(),
                    Role.ORDINARY.getLevel()
            );
            RegisterCache.setKey(uuid,userDto);

            try {
                //发布事件，发送激活邮件
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userDto, appUrl));
            } catch (Exception me) {
                //出现异常返回错误信息
                return ServerResponse.createByErrorMessage(ResponseCode.EMAIL_SEND_ERROR.getCode(),ResponseCode.EMAIL_SEND_ERROR.getDesc());
            }

            //成功
            return ServerResponse.createBySuccess("注册完成一半,请前往邮件继续确认");

        } else {
            //注册表单填写有错
            return ServerResponse.createByErrorMessage(errors.getAllErrors().get(0).getDefaultMessage());
        }

    }

    @RequestMapping(value = "/registrationConfirm/{key}")
    public ModelAndView registrationConfirm(@PathVariable String key) throws ExecutionException {
        ModelAndView mv = new ModelAndView();
        if(key == null || key.trim().length()!= 32 || RegisterCache.getKey(key) == null){
            mv.setViewName("/error");
            mv.addObject("msg","参数错误");
            return mv;
        }
        //开始注册
        UserDto userDto = RegisterCache.getKey(key);
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(MD5.md5(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole(Role.ORDINARY.getLevel());
        user.setLastLoginDatetime(new Date());

        ServerResponse res = userService.insertSelective(user);
        if(res.isSuccess()){
            mv.setViewName("/success");
            mv.addObject("msg","注册成功,去登录吧");
        }else{
            mv.setViewName("/error");
            mv.addObject("msg","注册失败,请重试");
        }
        return mv;
    }


    /**
     * 根据分类来获取任务
     * @param id
     * @return
     */
    @RequestMapping(value = {"/","/find/{id}/{pageNum}","/find/{id}"},method = RequestMethod.GET)
    public ModelAndView showListByCategory(@PathVariable(required = false) String id, @PathVariable(required = false) Integer pageNum, HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index");
        if(userDto != null){
            List<Object[]> listOrdered = categoryService.getCategoryOrdered(userDto.getId(),Const.CATEGORY_ROOT_ID);
            for(int i=0; i<listOrdered.size();i++) {
                StringBuilder str = new StringBuilder();
                for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                    str.append("&nbsp;&nbsp;");
                }
                listOrdered.get(i)[1] = str;
            }

            mv.addObject("allCategoryOrdered",listOrdered);
            if(pageNum == null){
                pageNum = 1;
            }
            if(id == null || id.length() != 32){
                id = null;
            }
            jobService.checkJobStatus(userDto.getId());
            PageInfo<JobListVo> list = jobService.listJobByCategory(pageNum,id,userDto.getId(),Const.JOB_NORMAL);
            for(JobListVo jlv : list.getList()){
                Long begin = jlv.getBeganTime().getTime();
                Long end = jlv.getEndTime().getTime();
                Long now = new Date().getTime();
                Number tmp = Math.ceil(1.0 * (now-begin)/(end-begin) * 100);
                jlv.setProgress(tmp.intValue());
            }
            mv.addObject("allJob",list);
            mv.addObject("cate_id",id);
        }

        return mv;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
//        session.setAttribute(Const.CURRENT_USER,null);
        session.removeAttribute(Const.CURRENT_USER);
        return mv;
    }

    @RequestMapping(value = "/forget",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forget(String username, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        if(username==null && username.trim().length() == 0){
            return ServerResponse.createByErrorMessage("参数错误");
        }

        return userService.forget(username,Const.URL_PREFIX);
    }

    @RequestMapping(value = "/reset",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView reset(String token, HttpServletRequest request) throws ExecutionException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/success");
        String value = TokenCache.getKey(token);
        if(token == null || value == null){
            mv.setViewName("/error");
            mv.addObject("msg","重置失败");
            return mv;
        }else{
            userService.reset(value);
            mv.addObject("msg","重置成功");
            return mv;
        }
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
