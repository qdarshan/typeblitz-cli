# TypeBlitz CLI

TypeBlitz is a terminal-based typing speed test application built with Java and the Micronaut framework. It allows users to practice typing with customizable difficulty levels, time limits, and word counts directly from the command line.

## Features

- **Interactive Typing Interface**: Real-time feedback on typing accuracy.
- **Customizable Difficulty**: Choose between EASY, MEDIUM, and HARD modes.
- **Configurable Session**: Set your own time limits and word counts.
- **Instant Feedback**: View WPM (Words Per Minute) and accuracy stats.

## Prerequisites

- Java 17 or higher
- Terminal with ANSI support

## Installation & Building

Clone the repository and build the project using Gradle:

```bash
./gradlew clean build
```

This will generate an executable JAR file in the `build/libs` directory (typically named `typeblitz-0.1-all.jar`).

## Usage

Run the application using the generated JAR file:

```bash
java -jar build/libs/typeblitz-0.1-all.jar [COMMAND] [OPTIONS]
```

### Start a Session

The primary command is `start`.

**Default Session (60s, 100 words, Easy):**
```bash
java -jar build/libs/typeblitz-0.1-all.jar start
```

**Custom Time Limit (e.g., 30 seconds):**
```bash
java -jar build/libs/typeblitz-0.1-all.jar start -t 30
```

**Custom Word Count (e.g., 50 words):**
```bash
java -jar build/libs/typeblitz-0.1-all.jar start -w 50
```

**Change Difficulty:**
Available levels: `EASY`, `MEDIUM`, `HARD`.
```bash
java -jar build/libs/typeblitz-0.1-all.jar start -d HARD
```

### Help

To see all available options:
```bash
java -jar build/libs/typeblitz-0.1-all.jar start --help
```