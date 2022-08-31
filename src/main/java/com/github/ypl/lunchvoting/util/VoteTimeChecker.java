package com.github.ypl.lunchvoting.util;

import com.github.ypl.lunchvoting.exception.IllegalRequestDataException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@Component
public class VoteTimeChecker {
    private final LocalTime boundaryTime = LocalTime.of(11, 0);
    private final Clock clock;

    public void checkVoteTime() {
        if (LocalTime.now(clock).isAfter(boundaryTime)) {
            throw new IllegalRequestDataException(
                    "Too late: " + LocalTime.now(clock).truncatedTo(ChronoUnit.MINUTES) + ". The re-vote must be sent no later than: " + boundaryTime
            );
        }
    }
}
