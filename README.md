# 🏆 Football World Cup Score Board

A clean and testable Java implementation of a live football scoreboard, built as a coding exercise.

## ✅ Features

- Start a new game between two teams (score starts at 0–0)
- Update the score of an existing game
- Finish a game and remove it from the scoreboard
- Display a **live summary** of all ongoing games, sorted by:
  1. Total score (descending)
  2. Most recently added (in case of a tie)

## 🛠 Tech Stack

- **Java 21**
- **Maven**
- **Lombok** – for boilerplate-free Java
- **Spock + Groovy** – for expressive BDD-style testing
- **SLF4J** – for logging

## 🧱 Project Structure

```
football-scoreboard/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/pl/afrackiewicz/scoreboard/
│   │       ├── Match.java
│   │       └── Scoreboard.java
│   └── test/
│       └── groovy/pl/afrackiewicz/scoreboard/
│           └── ScoreBoardSpec.groovy
```

## 📦 How to Build

Make sure you have Java 21+ and Maven installed.

```bash
mvn clean install
```

> IntelliJ users: enable **annotation processing** for Lombok under  
> `Settings > Build, Execution, Deployment > Compiler > Annotation Processors`.

## 🧪 How to Run Tests

```bash
mvn test
```

Or run them directly in IntelliJ using the file `ScoreBoardSpec.groovy`.

## 📌 Sorting Logic

Summary output is sorted by:

1. **Total score descending**
2. **Insertion order descending** (newer games first)

Implemented via a custom `insertionIndex` field in the `Match` class.

## 🔧 Example Summary Output

For the following games:

```
Mexico 0 - Canada 5
Spain 10 - Brazil 2
Germany 2 - France 2
Uruguay 6 - Italy 6
Argentina 3 - Australia 1
```

The summary will be:

```
Uruguay 6 - Italy 6
Spain 10 - Brazil 2
Mexico 0 - Canada 5
Argentina 3 - Australia 1
Germany 2 - France 2
```

## 📝 Assumptions

- Game uniqueness is determined by the combination of home and away team names.
- Scores can only be updated for existing games.
- Games are removed from the scoreboard after finishing.
- Sorting is consistent and testable, based on total score and order of creation.

## 🚀 Author

**Andrzej Frąckiewicz**  
Java Developer | TDD Enthusiast