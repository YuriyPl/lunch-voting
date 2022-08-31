package com.github.ypl.lunchvoting.web.user;

import com.github.ypl.lunchvoting.model.Role;
import com.github.ypl.lunchvoting.model.User;
import com.github.ypl.lunchvoting.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.EnumSet;

import static com.github.ypl.lunchvoting.config.SecurityConfig.PASSWORD_ENCODER;

@Slf4j
public abstract class AbstractUserController {

    @Autowired
    protected UserRepository repository;

    public ResponseEntity<User> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    protected User prepareAndSaveNewUser(User user) {
        user.setRoles(EnumSet.of(Role.USER));
        return prepareAndSave(user);
    }

    protected User prepareAndSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return repository.save(user);
    }
}
