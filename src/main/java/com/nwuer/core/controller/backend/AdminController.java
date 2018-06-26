package com.nwuer.core.controller.backend;

import com.github.pagehelper.PageInfo;
import com.mchange.v2.lang.StringUtils;
import com.nwuer.core.common.Const;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.common.util.MD5;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.service.impl.UserServiceImpl;
import com.nwuer.core.vo.RegistrationFormVo;
import com.nwuer.core.vo.UserListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author vividzc
 * @date 2018/6/23 10:40
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = {"/","/{pageNum}"},method = RequestMethod.GET)
    public ModelAndView index(@PathVariable(required = false) Integer pageNum) {
        if(pageNum == null){
            pageNum = 1;
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/index");
        PageInfo<UserListVo> allUser = userService.selectAllUser(pageNum);
        mv.addObject("allUser",allUser);
        return mv;
    }

    @RequestMapping(value = "/freeze/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse freezeUser(@PathVariable String id) {
        if(id == null || id.length() != 32) {
            return ServerResponse.createByErrorMessage("冻结失败");
        }
        return userService.freezeUser(id);
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteUser(@PathVariable String id ) {
        if(id == null || id.length() != 32) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return userService.delete(id);
    }

    /**
     * 激活
     * @return
     */
    @RequestMapping(value = "/active/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse activeUser(@PathVariable String id ){
        if(id == null || id.length() != 32) {
            return ServerResponse.createByErrorMessage("激活失败");
        }
        return userService.activeUser(id);
    }

    @RequestMapping(value = {"/search/","/search/{pageNum}"},method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView search(@PathVariable(required = false) Integer pageNum,String search_item,String search_value) {
        if(pageNum == null){
            pageNum = 1;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("search_item",search_item);
        mv.addObject("search_value",search_value);
        mv.setViewName("/admin/show_search");
        if(!StringUtils.nonWhitespaceString(search_item)||!StringUtils.nonWhitespaceString(search_value)
                || (!("id").equals(search_item)&& !("username").equals(search_item)&& !("email").equals(search_item))) {
            return mv;
        }

        PageInfo<UserListVo> allUser = userService.selectUserBySearch(pageNum,search_item,search_value);
        mv.addObject("allUser",allUser);
        return mv;
    }

    @RequestMapping(value = "/to_change_mes",method = RequestMethod.GET)
    public ModelAndView toChangePass(HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/change_mes");
        mv.addObject("userDto",userDto);
        return mv;
    }

    @RequestMapping(value = "/change_mes",method = RequestMethod.POST)
    public ModelAndView toChangePass(@Valid RegistrationFormVo registrationFormVo, Errors errors, HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userDto",session.getAttribute(Const.CURRENT_USER));
        mv.setViewName("/admin/change_mes");
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
            response.sendRedirect("/admin/");
        }else{
            //失败
            mv.addObject("msg",res.getMsg());
        }
        return mv;
    }



    @RequestMapping(value = "/to_search",method = RequestMethod.GET)
    public String toSearch() {
        return "/admin/user_search";
    }
}
