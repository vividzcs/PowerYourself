package com.nwuer.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author harbo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginFormVo {
    @NotNull(message = "用户名不能为空")
    @Size(min = 5, max = 16, message = "用户名最小不能小于5位，最大不能超过16位")
    private String username;

    @Pattern(regexp = "(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,18}$",
            message = "密码应至少包含数字、字母和符号中的两种，且长度6-18位")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
