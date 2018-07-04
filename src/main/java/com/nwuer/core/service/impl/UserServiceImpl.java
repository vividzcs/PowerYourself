package com.nwuer.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nwuer.core.common.Const;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.common.util.MD5;
import com.nwuer.core.common.util.TokenCache;
import com.nwuer.core.dao.UserMapper;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.entity.User;
import com.nwuer.core.pojo.Role;
import com.nwuer.core.pojo.VerificationToken;
import com.nwuer.core.service.IUserService;
import com.nwuer.core.vo.LoginFormVo;
import com.nwuer.core.vo.RegistrationFormVo;
import com.nwuer.core.vo.UserListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @author harbo
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SendSimpleEmailService sendSimpleEmailService;

    @Override
    public User findTopUserByUsername(UserDto user) {
        return null;
    }

    /**
     *
     * @param email
     * @return true 存在 false不存在
     */
    @Override
    public boolean verifyEmailExistence(String email) {
        int count = userMapper.verifyEmailExistence(email);
        return count > 0;
    }

    public ServerResponse insertSelective(User user) {
        int affectedRow = userMapper.insertSelective(user);
        if(affectedRow >0 ){
            return ServerResponse.createBySuccess("注册成功");
        }else {
            return ServerResponse.createByErrorMessage("注册失败");
        }
    }

    @Override
    public boolean verifyUsernameExistence(String username) {
        int count = userMapper.verifyUsernameExistence(username);
        return count > 0;
    }

    @Override
    public ServerResponse login(LoginFormVo loginFormVo) {
        UserDto userDto = userMapper.findByUsernameAndPassword(loginFormVo.getUsername(), MD5.md5(loginFormVo.getPassword()));
        if (userDto != null) {
            userMapper.updateLoginTime(userDto.getId(),new Date());
            return ServerResponse.createBySuccess(userDto);
        } else {
            return ServerResponse.createByErrorMessage("用户名或密码错误或未激活");
        }
    }

    @Override
    public User getUser(String verificationToken) {
//        return verificationTokenRepository.findTopByToken(verificationToken).getUser();
        return null;
    }

    @Override
    public void saveRegisteredUser(User user) {
//        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
//        VerificationToken myToken = new VerificationToken(token, user);
//        verificationTokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
//        return verificationTokenRepository.findTopByToken(VerificationToken);
        return null;
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        return null;
    }

    @Override
    public ServerResponse forget(String username,String url) throws UnsupportedEncodingException, MessagingException {
        //先检查是否有这个用户
        UserDto userDto = userMapper.hasUser(username);
        if(userDto == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        //再设置token
        TokenCache.setKey(Const.TOKEN_PREFIX+userDto.getId(),userDto.getId());
        //发送邮件
        sendSimpleEmailService.sendEmail(userDto,url);

        return ServerResponse.createBySuccess("请登录邮箱重置密码");
    }

    @Override
    @Transactional
    public void reset(String token) {
        userMapper.reset(token,MD5.md5(Const.INITAIL_PASSWORD));
    }

    public UserDto selectById(String id){
        return userMapper.selectById(id);
    }

    public PageInfo<UserListVo> selectAllUser(Integer pageNum){
        PageHelper.startPage(pageNum, Const.PAGE_SIZE);
        List<UserListVo> list = userMapper.selectAll(Role.ORDINARY.getLevel());
        PageInfo<UserListVo> page = new PageInfo<UserListVo>(list);
        return page;
    }

    public ServerResponse freezeUser(String id) {
        int affectCount = userMapper.freezeUser(id);
        if(affectCount>0){
            return ServerResponse.createBySuccess("冻结成功");
        }else {
            return ServerResponse.createByErrorMessage("冻结失败");
        }
    }

    public ServerResponse activeUser(String id) {
        int affectCount = userMapper.activeUser(id);
        if(affectCount>0){
            return ServerResponse.createBySuccess("激活成功");
        }else {
            return ServerResponse.createByErrorMessage("激活失败");
        }
    }

    @Transactional
    public ServerResponse delete(String id) {
        int affectCount = userMapper.deleteByPrimaryKey(id);
        if(affectCount>0){
            return ServerResponse.createBySuccess("删除成功");
        }else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    public PageInfo<UserListVo> selectUserBySearch(Integer pageNum,String search_item, String search_value) {
        PageHelper.startPage(pageNum, Const.PAGE_SIZE);
        List<UserListVo> list = userMapper.selectUserBySearch(search_item,search_value);
        PageInfo<UserListVo> page = new PageInfo<UserListVo>(list);
        return page;
    }

    public ServerResponse updateByFormVo(RegistrationFormVo registrationFormVo) {
        int affected = userMapper.updateUserSelective(registrationFormVo);

        if(affected > 0){
            return ServerResponse.createBySuccess("修改成功");
        }else {
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    public ServerResponse usernameCanUpdate(String id, String username) {
        int row = userMapper.usernameCanUpdate(id,username);
        if(row > 0){
            return ServerResponse.createByErrorMessage("用户名已被占用");
        }else {
            return  ServerResponse.createBySuccess("可以修改");
        }
    }

    public ServerResponse emailCanUpdate(String id, String email) {
        int row = userMapper.emailCanUpdate(id,email);
        if(row > 0){
            return ServerResponse.createByErrorMessage("you'xiang邮箱已被占用");
        }else {
            return  ServerResponse.createBySuccess("可以修改");
        }
    }
}
