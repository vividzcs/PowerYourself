package com.nwuer.core.controller.user;

import com.nwuer.core.common.Const;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.entity.Task;
import com.nwuer.core.service.impl.CategoryService;
import com.nwuer.core.service.impl.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author vividzc
 * @date 2018/6/23 23:23
 */
@Controller
@RequestMapping("/user/job")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listJob(HttpSession session){
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/job");

        List<Task> list = jobService.listJob(user.getId());
        List<Object[]> listOrdered = categoryService.getCategoryOrdered(user.getId(),"0");
        for(int i=0; i<listOrdered.size();i++) {
            StringBuilder str = new StringBuilder();
            for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                str.append("&nbsp;&nbsp;");
            }
            listOrdered.get(i)[1] = str;
        }

        mv.addObject("allJob",list);
        mv.addObject("allCategoryOrdered",listOrdered);
        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView addJob(HttpSession session){
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/job_post");

        List<Task> list = jobService.listJob(user.getId());
        List<Object[]> listOrdered = categoryService.getCategoryOrdered(user.getId(),"0");
        for(int i=0; i<listOrdered.size();i++) {
            StringBuilder str = new StringBuilder();
            for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                str.append("&nbsp;&nbsp;");
            }
            listOrdered.get(i)[1] = str;
        }

        mv.addObject("allCategoryOrdered",listOrdered);
        return mv;
    }
}
