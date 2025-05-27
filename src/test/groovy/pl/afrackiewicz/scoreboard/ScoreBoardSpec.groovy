package pl.afrackiewicz.scoreboard

import spock.lang.Specification

class ScoreBoardSpec extends Specification {
    def scoreboard = new Scoreboard();

    def "should start a game"() {
        when:
        scoreboard.startGame("Mexico", "Canada")

        then:
        scoreboard.getSummary().size() == 1
        scoreboard.getSummary().first.homeTeam == "Mexico"
        scoreboard.getSummary().first.awayTeam == "Canada"
        scoreboard.getSummary().first.homeScore == 0
        scoreboard.getSummary().first.awayScore == 0
    }

    def "should not allow starting the same game twice"() {
        given:
        scoreboard.startGame("Spain", "Brazil")

        when:
        scoreboard.startGame("Spain", "Brazil")

        then:
        thrown(IllegalArgumentException)
    }

    def "should update the score of a game"() {
        given:
        scoreboard.startGame("Germany", "France")

        when:
        scoreboard.updateScore("Germany", "France", 2, 2)

        then:
        def match = scoreboard.getSummary().find { it.homeTeam == "Germany" && it.awayTeam == "France" }
        match.homeScore == 2
        match.awayScore == 2
    }

    def "should remove game after finish"() {
        given:
        scoreboard.startGame("Argentina", "Australia")

        when:
        scoreboard.finishGame("Argentina", "Australia")

        then:
        scoreboard.getSummary().isEmpty()
    }

    def "should return sorted summary by total score and recency"() {
        given:
        scoreboard.startGame("Mexico", "Canada")
        scoreboard.updateScore("Mexico", "Canada", 0, 5)

        scoreboard.startGame("Spain", "Brazil")
        scoreboard.updateScore("Spain", "Brazil", 10, 2)

        scoreboard.startGame("Germany", "France")
        scoreboard.updateScore("Germany", "France", 2, 2)

        scoreboard.startGame("Uruguay", "Italy")
        scoreboard.updateScore("Uruguay", "Italy", 6, 6)

        scoreboard.startGame("Argentina", "Australia")
        scoreboard.updateScore("Argentina", "Australia", 3, 1)

        when:
        def summary = scoreboard.getSummary()

        then:
        summary*.toString() == [
                "Uruguay 6 - Italy 6",
                "Spain 10 - Brazil 2",
                "Mexico 0 - Canada 5",
                "Argentina 3 - Australia 1",
                "Germany 2 - France 2"
        ]
    }
}
