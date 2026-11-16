# Module 3: Measure, then close the loop

Five small classes in `src/main`. Some have tests, some don't. Don't trust the tests, measure them.

## Run the numbers

```bash
# tests + coverage (JaCoCo): open module-3/target/site/jacoco/index.html
./mvnw -pl module-3 test

# mutation testing (PIT): open module-3/target/pit-reports/index.html
./mvnw -pl module-3 test-compile org.pitest:pitest-maven:mutationCoverage
```

JaCoCo's report shows line coverage, branch coverage and cyclomatic complexity per class.
It does not compute CRAP. You (or the AI) can calculate it from `target/site/jacoco/jacoco.csv`.

## Your task

1. Measure: line coverage, branch coverage, complexity, CRAP and the PIT mutation score for each class.
2. Sort the five classes into profiles. Which are complex, which are covered, which only look fine?
3. One class has 100% line and branch coverage and still leaks mutants. Find it.
4. Give the AI the surviving mutants and coverage gaps, and have it improve the tests. Measure again.
5. Repeat until the numbers stop improving. Decide which remaining gaps matter.

You decide what's worth fixing. The tools tell you what is true, not what is important.
