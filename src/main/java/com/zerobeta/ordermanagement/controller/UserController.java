package com.zerobeta.ordermanagement.controller;

import com.zerobeta.ordermanagement.dto.SignInRequest;
import com.zerobeta.ordermanagement.dto.SignUpRequest;
import com.zerobeta.ordermanagement.service.UserService;
import com.zerobeta.ordermanagement.securityConfig.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Order Management System's Auth API !";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
            return userService.signUpUser(signUpRequest);
    }


    @PostMapping("/signin")
    public String signIn(@RequestBody SignInRequest signInRequest){
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));
            System.out.println(authenticate.isAuthenticated());
            if (authenticate.isAuthenticated()) {
                return jwtService.generateToken(signInRequest.getUserName());
            } else {
                throw new UsernameNotFoundException("Invalid user request");
            }
        } catch (AuthenticationException e) {
            return  e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

}
