package com.github.ypl.lunchvoting.web.vote;

import com.github.ypl.lunchvoting.model.Vote;
import com.github.ypl.lunchvoting.repository.VoteRepository;
import com.github.ypl.lunchvoting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.ypl.lunchvoting.web.restaurant.RestaurantTestData.RESTAURANT_1_ID;
import static com.github.ypl.lunchvoting.web.user.UserTestData.ADMIN_MAIL;
import static com.github.ypl.lunchvoting.web.user.UserTestData.USER_ID;
import static com.github.ypl.lunchvoting.web.user.UserTestData.USER_MAIL;
import static com.github.ypl.lunchvoting.web.vote.VoteTestData.ADMIN_VOTE_ID;
import static com.github.ypl.lunchvoting.web.vote.VoteTestData.NOT_FOUND;
import static com.github.ypl.lunchvoting.web.vote.VoteTestData.USER_VOTE_ID;
import static com.github.ypl.lunchvoting.web.vote.VoteTestData.VOTE_MATCHER;
import static com.github.ypl.lunchvoting.web.vote.VoteTestData.getNew;
import static com.github.ypl.lunchvoting.web.vote.VoteTestData.userOldVote;
import static com.github.ypl.lunchvoting.web.vote.VoteTestData.userVote;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteController.REST_URL + "/";
    private static final String POST_URL = "/api/restaurants/" + RESTAURANT_1_ID + "/vote";

    @Autowired
    private VoteRepository repository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + USER_VOTE_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(userVote));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + USER_VOTE_ID))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(POST_URL))
                .andDo(print())
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(repository.getExisted(newId), newVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createTimeConflict() throws Exception {
        perform(MockMvcRequestBuilders.post(POST_URL))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + USER_VOTE_ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        VOTE_MATCHER.assertMatch(repository.getAll(USER_ID), userOldVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void deleteDataConflict() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + ADMIN_VOTE_ID))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(userVote, userOldVote));
    }
}
