package pl.afrackiewicz.scoreboard;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = {"homeTeam", "awayTeam"})
public class Match {

    private final String homeTeam;
    private final String awayTeam;
    private int homeScore = 0;
    private int awayScore = 0;
    private final LocalDateTime startTime = LocalDateTime.now();
    private final long insertionIndex;

    public void updateScore(int home, int away) {
        this.homeScore = home;
        this.awayScore = away;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }
}
