package com.nwuer.core.common.event;

import com.nwuer.core.controller.IndexController;
import com.nwuer.core.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 一个事件类
 * @see IndexController#register(com.nwuer.core.vo.RegistrationFormVo, org.springframework.validation.Errors, org.springframework.web.context.request.WebRequest)
 * @author zengxiaogang
 */
@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    /**
     * 请求的url。也就是域名了
     */
    private String appUrl;

    /**
     * 注册完成dao返回的用户类
     */
    private UserDto user;

    public OnRegistrationCompleteEvent(UserDto user, String appUrl) {
        super(user);

        this.user = user;
        this.appUrl = appUrl;
    }
}
