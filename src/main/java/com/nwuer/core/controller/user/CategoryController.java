package com.nwuer.core.controller.user;

import com.nwuer.core.common.Const;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.entity.Category;
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
import java.util.Map;

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
                                          Map<String,String> map,
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

        List<Category> list = categoryService.listCategory(user.getId());
        List<Object[]> listOrdered = categoryService.getCategoryOrdered(user.getId(),"0");
        for(int i=0; i<listOrdered.size();i++) {
            StringBuilder str = new StringBuilder();
            for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                str.append("&nbsp;&nbsp;");
            }
            listOrdered.get(i)[1] = str;
        }

        mv.addObject("allCategory",list);
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

//    @RequestMapping(value = "/test",method = RequestMethod.POST)
//    @ResponseBody
//    public List<Object[]> test(){
//    }

}
