package com.zerobeta.ordermanagement.securityConfig;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails ud = (UserInfoDetails) authentication.getPrincipal();
        return ud.getUserId();
    }
}