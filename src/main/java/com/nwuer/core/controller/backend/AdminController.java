package com.nwuer.core.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author vividzc
 * @date 2018/6/23 10:40
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/")
    public String index(Map<String,Object> map) {

        return "admin/index";
    }
}
