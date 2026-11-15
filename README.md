# AI Workshop Exercises

Hands-on exercises for the workshop on building quality software with AI.
Java 17, Maven, TestNG, AssertJ. Standalone build, every dependency comes from Maven Central.

## Prerequisites

- JDK 17
- Internet access to Maven Central
- Claude Code

## Build

```bash
./mvnw compile                # everything
./mvnw -pl module-1 compile   # a single module
```

## Modules

| Module | What it is |
|---|---|
| `module-1` | A small bookstore order processor. Read it and find as many problems as you can. Don't fix anything yet. |

Run the demo: `./mvnw -q -pl module-1 compile exec:java -Dexec.mainClass=com.example.workshop.module1.Main`
