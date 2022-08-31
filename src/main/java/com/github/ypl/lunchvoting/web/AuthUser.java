package com.github.ypl.lunchvoting.web;

import com.github.ypl.lunchvoting.model.User;
import org.springframework.lang.NonNull;

public class AuthUser extends org.springframework.security.core.userdetails.User {
    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }

    public User getUser() {
        return user;
    }
}
