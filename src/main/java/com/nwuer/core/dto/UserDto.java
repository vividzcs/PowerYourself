package com.nwuer.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author harbo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    /**
     * 用户名
     */
    @NotEmpty
    private String username;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

    /**
     * 电子邮件
     */
    @NotEmpty
    private String email;

}
