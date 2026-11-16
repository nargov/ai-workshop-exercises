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
| `module-2` | A product definition for a public library system. Use the AI to produce an implementation plan. No code. |
| `module-3` | Five small classes with different quality profiles. Measure coverage, complexity and mutation score, then improve the tests. |
| `module-4` | Build "Wiki Pulse", a live dashboard of Wikipedia edits, from examples, using subagents. Includes a recorded sample of the feed. |

Run the demo: `./mvnw -q -pl module-1 compile exec:java -Dexec.mainClass=com.example.workshop.module1.Main`
