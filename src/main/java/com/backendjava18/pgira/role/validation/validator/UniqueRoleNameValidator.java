package com.backendjava18.pgira.role.validation.validator;

import com.backendjava18.pgira.role.model.Role;
import com.backendjava18.pgira.role.repository.RoleRepository;
import com.backendjava18.pgira.role.validation.anontation.UniqueRoleName;
import com.sun.istack.logging.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName,String> {

    private static final Logger Log = Logger.getLogger(UniqueRoleNameValidator.class);
    private String message;
    private final RoleRepository roleRepository;

    public UniqueRoleNameValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void initialize(UniqueRoleName constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        Optional<Role> roleOptional = roleRepository.findByName(name);

        Log.info("call is valid");
        if(roleOptional.isEmpty()){
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
