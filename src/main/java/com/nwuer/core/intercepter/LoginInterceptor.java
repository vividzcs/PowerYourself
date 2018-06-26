package com.nwuer.core.intercepter;

import com.nwuer.core.common.Const;
import com.nwuer.core.common.util.TokenCache;
import com.nwuer.core.dto.UserDto;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author vividzc
 * @date 2018/6/25 21:54
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是邮件那边过来的,检测后放过
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER); //获取登录的session信息

        String queryString = request.getQueryString();
        if(queryString != null && queryString.trim().length() >0 ){
            String[] tmp = queryString.split("&");
            if(tmp.length == 2){
                String[] userId = tmp[0].split("=");
                String[] jobId = tmp[1].split("=");
                String value = TokenCache.getKey(Const.JOB_TOKEN_PREFIX+jobId[1]);
                if("primary".equals(userId[0]) && "primary1".equals(jobId[0])) {

                    if (value == null) {
                        //说明不对
                        response.sendRedirect(request.getContextPath() + "/to_login");  //自动跳转界面
                        return false;
                    } else {
                        if (user == null) {
                            UserDto u = new UserDto();
                            u.setId(userId[1]);
                            session.setAttribute(Const.CURRENT_USER, u);
                        }
                        return true;
                    }
                }
            }

        }

        String url = request.getServletPath();


        if(user!=null){
            if(url.startsWith("/user/") && "10".equals(user.getRole())){
                return true;
            }else if(url.startsWith("/admin/") && "1".equals(user.getRole())){
                return true;
            }

        }

        response.sendRedirect(request.getContextPath()+"/to_login");  //未登录自动跳转界面
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
