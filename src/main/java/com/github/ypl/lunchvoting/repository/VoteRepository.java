package com.github.ypl.lunchvoting.repository;

import com.github.ypl.lunchvoting.exception.DataConflictException;
import com.github.ypl.lunchvoting.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.user.id=:userId ORDER BY v.added")
    Optional<Vote> get(int id, int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.added=CURRENT_DATE")
    Vote getActiveUserVote(int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId")
    List<Vote> getAll(int userId);

    default Vote checkBelong(int id, int userId) {
        return get(id, userId).orElseThrow(
                () -> new DataConflictException("Vote id=" + id + " doesn't belong to User id=" + userId));
    }
}
