package com.github.ypl.lunchvoting.web.vote;

import com.github.ypl.lunchvoting.model.Vote;
import com.github.ypl.lunchvoting.repository.VoteRepository;
import com.github.ypl.lunchvoting.service.VoteService;
import com.github.ypl.lunchvoting.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/api/votes";
    static final String POST_REST_URL = "/api/restaurants/{restaurantId}/vote";

    private final VoteRepository repository;
    private final VoteService service;

    @GetMapping(VoteController.REST_URL + "/{id}")
    public ResponseEntity<Vote> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("get vote {} for user {}", id, authUser.id());
        return ResponseEntity.of(repository.get(id, authUser.id()));
    }

    @PostMapping(POST_REST_URL)
    public ResponseEntity<Vote> create(@AuthenticationPrincipal AuthUser authUser, @PathVariable int restaurantId) {
        log.info("create vote for user {}", authUser.id());
        Vote created = service.save(new Vote(), authUser.id(), restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(VoteController.REST_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("delete vote {} for user {}", id, authUser.id());
        repository.checkBelong(id, authUser.id());
        repository.delete(id);
    }

    @GetMapping(VoteController.REST_URL)
    public List<Vote> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAll for user {}", authUser.id());
        return repository.getAll(authUser.id());
    }
}
