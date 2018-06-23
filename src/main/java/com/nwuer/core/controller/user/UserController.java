package com.nwuer.core.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author vividzc
 * @date 2018/6/23 13:28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/")
    public String index(Map<String,Object> map) {

        return "user/index";
    }

}
