package com.zerobeta.ordermanagement.securityConfig;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();

    long getUserId();
}
