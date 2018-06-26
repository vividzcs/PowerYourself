package com.nwuer.core.controller.user;

import com.nwuer.core.common.Const;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.service.impl.CategoryService;
import com.nwuer.core.vo.CategoryFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vividzc
 * @date 2018/6/23 16:03
 */
@Controller
@RequestMapping("/user/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(@Valid CategoryFormVo categoryFormVo,
                                          Errors errors,
                                          HttpSession session){
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        if(!errors.hasErrors()) {
            categoryFormVo.setUserId(user.getId());
            ServerResponse res = categoryService.addCategory(categoryFormVo);
            return res;
        }else{
            //有错
            return ServerResponse.createByErrorMessage("参数错误");
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listCategory(HttpSession session){
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/category_list");

        List<Object[]> listOrdered = categoryService.getCategoryOrdered(user.getId(),Const.CATEGORY_ROOT_ID);
        for(int i=0; i<listOrdered.size();i++) {
            StringBuilder str = new StringBuilder("|");
            for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                str.append("--");
            }
            listOrdered.get(i)[1] = str;
        }

        mv.addObject("allCategoryOrdered",listOrdered);
        return mv;
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delete(@PathVariable String id){
        if(id.length() != Const.ID_LENGTH) {
            return ServerResponse.createByErrorMessage("系统错误");
        }
        return categoryService.delete(id);
    }

    /**
     * 编辑分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id,HttpSession session) {
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        if(id == null || id.trim().length() !=32){
            mv.setViewName("/error");
            mv.addObject("msg","参数错误");
            return mv;
        }
        //查出来

        ServerResponse res = categoryService.selectById(id);
        if(res.isSuccess()){
            List<Object[]> listOrdered = categoryService.getCategoryOrderedForUpdate(user.getId(),id,Const.CATEGORY_ROOT_ID);
            for(int i=0; i<listOrdered.size();i++) {
                StringBuilder str = new StringBuilder("|");
                for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                    str.append("--");
                }
                listOrdered.get(i)[1] = str;
            }

            mv.addObject("allCategoryOrdered",listOrdered);
            mv.setViewName("/user/category_edit");
            mv.addObject("category",res.getData());
        }else {
            mv.setViewName("/error");
            mv.addObject("msg","参数错误");
        }
        return mv;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update(@Valid CategoryFormVo categoryFormVo,
                                 Errors errors,
                                 HttpSession session){
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        if(!errors.hasErrors()) {
            categoryFormVo.setUserId(user.getId());
            ServerResponse res = categoryService.update(categoryFormVo);
            return res;
        }else{
            //有错
            return ServerResponse.createByErrorMessage("参数错误");
        }
    }
//    @RequestMapping(value = "/test",method = RequestMethod.POST)
//    @ResponseBody
//    public List<Object[]> test(){
//    }

}
