package com.github.ypl.lunchvoting.web.user;

import com.github.ypl.lunchvoting.MatcherFactory;
import com.github.ypl.lunchvoting.model.Role;
import com.github.ypl.lunchvoting.model.User;
import com.github.ypl.lunchvoting.util.JsonUtil;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "password", "votes");

    public static int ADMIN_ID = 1;
    public static int USER_ID = 2;
    public static final int NOT_FOUND = 100;
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String USER_MAIL = "user@yandex.ru";

    public static User admin = new User(ADMIN_ID, "admin", ADMIN_MAIL, "admin123", Role.USER, Role.ADMIN);
    public static User user = new User(USER_ID, "user", USER_MAIL, "user11", Role.USER);

    public static User getNew() {
        return new User(null, "newUser", "newemail@gmail.com", "12345", Role.USER);
    }

    public static User getUpdated() {
        return new User(USER_ID, "updatedName", "updated@mail.ru", "updatedPassword", Role.USER);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
