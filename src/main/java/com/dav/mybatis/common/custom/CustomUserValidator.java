package com.dav.mybatis.common.custom;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dav.mybatis.model.User;

@Component
public class CustomUserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        try {
            User user = (User) o;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
            if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
                errors.rejectValue("username", "Size.userForm.username");
            }
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
            if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
                errors.rejectValue("password", "Size.userForm.password");
            }
            if (!user.getPasswordConfirm().equals(user.getPassword())) {
                errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
