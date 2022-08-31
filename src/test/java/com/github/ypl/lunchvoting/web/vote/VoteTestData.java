package com.github.ypl.lunchvoting.web.vote;

import com.github.ypl.lunchvoting.MatcherFactory;
import com.github.ypl.lunchvoting.model.Vote;

import java.time.LocalDate;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user", "restaurant");

    public static final int ADMIN_VOTE_ID = 1;
    public static final int USER_VOTE_ID = 2;
    public static final int USER_OLD_VOTE_ID = 3;
    public static final int NOT_FOUND = 100;

    public static Vote adminVote = new Vote(ADMIN_VOTE_ID, LocalDate.of(2022, 5, 25));
    public static Vote userVote = new Vote(USER_VOTE_ID, LocalDate.now());
    public static Vote userOldVote = new Vote(USER_OLD_VOTE_ID, LocalDate.of(2022, 5, 25));

    public static Vote getNew() {
        return new Vote();
    }
}
