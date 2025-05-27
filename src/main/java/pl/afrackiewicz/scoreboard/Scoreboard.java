package pl.afrackiewicz.scoreboard;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Scoreboard {
    private final Map<String, Match> matches = new LinkedHashMap<>();
    private long indexCounter = 0;

    public void startGame(String homeTeam, String awayTeam) {
        String key = keyFor(homeTeam, awayTeam);
        if(matches.containsKey(key)) {
            log.warn("Game between {} and {} is already started", homeTeam, awayTeam);
            throw new IllegalArgumentException("Game already started");
        }
        Match match = new Match(homeTeam, awayTeam, indexCounter++);
        matches.put(key, match);
        log.info("Game started: {}", match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = getMatchOrThrow(homeTeam, awayTeam);
        match.updateScore(homeScore, awayScore);
        log.info("Score updated: {}", match);
    }

    public void finishGame(String homeTeam, String awayTeam) {
        Match match = getMatchOrThrow(homeTeam, awayTeam);
        matches.remove(keyFor(homeTeam, awayTeam));
        log.info("Game finished: {}", match);
    }

    public List<Match> getSummary() {
        return matches.values().stream()
                .sorted(
                        Comparator.comparingInt(Match::getTotalScore).reversed()
                                .thenComparing(Comparator.comparingLong(Match::getInsertionIndex).reversed())
                )
                .toList();
    }

    private Match getMatchOrThrow(String homeTeam, String awayTeam) {
        String key = keyFor(homeTeam, awayTeam);
        Match match = matches.get(key);
        if(match == null) {
            log.error("No match found for team {} and team {}", homeTeam, awayTeam);
            throw new NoSuchElementException("Game not found");
        }
        return match;
    }

    private String keyFor(String home, String away) {
        return home + ":" + away;
    }
}
