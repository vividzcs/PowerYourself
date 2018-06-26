package com.nwuer.core.service;


import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.entity.User;
import com.nwuer.core.pojo.VerificationToken;
import com.nwuer.core.vo.LoginFormVo;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * @author harbo
 */
public interface IUserService {
    /**
     * 查找某用户的业务逻辑
     * @param user 用户对象
     * @return 那个用户
     */
    User findTopUserByUsername(UserDto user);


    boolean verifyEmailExistence(String email);

    boolean verifyUsernameExistence(String username);

    /**
     * 登录的业务逻辑
     * @param userDto 登录的dto对象
     */
//    UserDto login(UserDto userDto);

    ServerResponse<String> login(LoginFormVo loginFormVo);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String oldToken);

    ServerResponse forget(String username,String url) throws UnsupportedEncodingException, MessagingException;

    void reset(String token);
}
