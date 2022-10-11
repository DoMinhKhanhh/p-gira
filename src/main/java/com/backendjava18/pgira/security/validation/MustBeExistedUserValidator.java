package com.backendjava18.pgira.security.validation;

import com.backendjava18.pgira.role.model.User;
import com.backendjava18.pgira.role.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MustBeExistedUserValidator implements ConstraintValidator<MustBeExistedUser, String> {

    private final UserRepository userRepository;
    private String message;


    public MustBeExistedUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(MustBeExistedUser constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        User user = userRepository.findByUsername(username)
                .orElse(null);

        if (user != null) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
