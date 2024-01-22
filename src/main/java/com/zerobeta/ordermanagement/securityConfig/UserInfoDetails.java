package com.zerobeta.ordermanagement.securityConfig;

import com.zerobeta.ordermanagement.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
public class UserInfoDetails implements UserDetails {
    String userName=null;
    String password = null;

    Long userId=null;
    List<GrantedAuthority> authorities;
    public UserInfoDetails(User user){
        userName= user.getEmail();
        password= user.getPassword();
        userId = user.getId();
        authorities= Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        System.out.println(userName);
        System.out.println(password);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public Long getUserId(){return userId;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
