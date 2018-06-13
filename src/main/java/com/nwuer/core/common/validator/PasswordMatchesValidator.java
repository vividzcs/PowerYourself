package com.nwuer.core.common.validator;


import com.nwuer.core.common.ResponseCode;
import com.nwuer.core.common.annotation.PasswordMatches;
import com.nwuer.core.common.exception.PowerYourselfException;
import com.nwuer.core.vo.RegistrationFormVo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author harbo
 * @date 6.13 23:44
 *
 * 密码验证器
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegistrationFormVo> {


    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    /**
     * 验证的过程
     * @param value 注册提交的VO
     * @param context 没用上的context
     * @return 密码是否一致
     */
    @Override
    public boolean isValid(RegistrationFormVo value, ConstraintValidatorContext context) {
        if (value.getPassword() == null) {
            throw new PowerYourselfException(ResponseCode.PASSWORD_FILL_IN_REQUIRED);
        }
        if (value.getConformPassword() == null) {
            throw new PowerYourselfException(ResponseCode.CONFORM_PASSWORD_FILL_IN_REQUIRED);
        }
        return value.getPassword().equals(value.getConformPassword());
    }

}
