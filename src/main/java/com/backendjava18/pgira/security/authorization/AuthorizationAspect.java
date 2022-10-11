package com.backendjava18.pgira.security.authorization;

import com.backendjava18.pgira.common.exception.GiraBusinessException;
import com.backendjava18.pgira.role.model.Operation;
import com.backendjava18.pgira.role.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component
public class AuthorizationAspect {
    private final OperationRepository operationRepository;

    public AuthorizationAspect(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    // define point cut
    @Before("@annotation(giraOperation)")
    public void authorizationOperation(GiraOperation giraOperation) {
        log.info("Pointcut has been activated, operation = " + giraOperation.name());

        //get current user
        String username = getCurrentUser();

        // check permission
        if (!isPermitted(username, giraOperation.name())) {
            throw new GiraBusinessException(
                    "User is not permitted to use this operation. Please contact adminstrators for permission."
            );
        }
    }

    private String getCurrentUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null)
            return null;

        String principal = (String) auth.getPrincipal();
        if (!principal.isEmpty()) {
            return principal;
        }

        UserDetails currentUser = (UserDetails) auth.getPrincipal();
        return currentUser.getUsername();
    }

    private Boolean isPermitted(String username, String operationName) {
        List<Operation> permittedOperations
                = operationRepository.findAllByNameAndUsername(operationName, username);

        return !permittedOperations.isEmpty();
    }
}
