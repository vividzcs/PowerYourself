package com.nwuer.core.dao;

import com.nwuer.core.dto.UserDto;
import com.nwuer.core.entity.User;
import com.nwuer.core.vo.RegistrationFormVo;
import com.nwuer.core.vo.UserListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    UserDto findByUsernameAndPassword(@NotEmpty @Param("username") String username, @Param("password") String password);

    void updateLoginTime(@Param("id") String id, @Param("date") Date date);

    UserDto hasUser(String username);

    void reset(@Param("token") String token, @Param("passNew") String passNew);

    UserDto selectById(String id);

    List<UserListVo> selectAll(String level);

    int freezeUser(String id);

    int activeUser(String id);

    List<UserListVo> selectUserBySearch(@Param("search_item") String search_item, @Param("search_value") String search_value);

    int verifyEmailExistence(String email);

    int verifyUsernameExistence(String username);

    int updateUserSelective(RegistrationFormVo registrationFormVo);
}