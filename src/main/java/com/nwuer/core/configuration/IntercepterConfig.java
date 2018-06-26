package com.nwuer.core.configuration;

import com.nwuer.core.intercepter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author vividzc
 * @date 2018/6/25 21:29
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer{
    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPattern后跟拦截地址，excludePathPatterns后跟排除拦截地址
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(new String[]{"/user/**","/admin/**"});
    }
}
