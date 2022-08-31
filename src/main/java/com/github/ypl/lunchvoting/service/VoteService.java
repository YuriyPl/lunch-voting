package com.github.ypl.lunchvoting.service;

import com.github.ypl.lunchvoting.model.Vote;
import com.github.ypl.lunchvoting.repository.RestaurantRepository;
import com.github.ypl.lunchvoting.repository.UserRepository;
import com.github.ypl.lunchvoting.repository.VoteRepository;
import com.github.ypl.lunchvoting.util.VoteTimeChecker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final VoteTimeChecker voteTimeChecker;

    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        Vote activeVote = voteRepository.getActiveUserVote(userId);
        if (activeVote != null) {
            voteTimeChecker.checkVoteTime();
            vote.setId(activeVote.getId());
        }

        vote.setUser(userRepository.getReferenceById(userId));
        vote.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return voteRepository.save(vote);
    }
}
