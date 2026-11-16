# Module 5: Write a skill

**Time:** 15 minutes. **Output:** a skill in this repo, tried on at least two code sections.

## The exercise

In module 3 you ran coverage and mutation testing by hand, and worked out CRAP yourself.
Now teach Claude to do it for you, as a skill.

Create a skill at `.claude/skills/code-quality/SKILL.md` in your clone of this repo.
When you run it on a section of code, it should:

1. Run the tests with coverage, and run mutation testing on that code.
2. Calculate **CRAP** for each method.
3. Give a short summary of the code's quality: the mutation score, the worst methods by CRAP,
   the surviving mutants that matter most, and what you would fix first.

**If the section of code isn't clear, the skill must ask the user what to run it on**,
and not guess.

### Steps

1. Ask Claude to write the skill. Give it the three requirements above and a sentence or two
   about the tools (`module-3/README.md` has the commands).
2. Read what it wrote. Is the `description` good enough that Claude would pick this skill
   when you ask "how good are the tests for DiscountPolicy?" Does it say what to do when the
   input is unclear?
3. Run it explicitly, with a clear target: `/code-quality LoyaltyPoints`
4. Run it with no target: `/code-quality`. It should ask you what to run on.
5. Ask in plain language, without the slash command: "how good are the tests for DiscountPolicy?"
   Does the skill start on its own?
6. Fix what didn't work, and run it again.

### Notes

- The mutation and coverage setup lives in `module-3/pom.xml`. Point the skill at the classes in `module-3`.
- JaCoCo's `jacoco.csv` has one row per class. For CRAP per method, you need the XML report,
  `module-3/target/site/jacoco/jacoco.xml`.
- PIT can run on one class: `-DtargetClasses=... -DtargetTests=...`.

### Debrief

- What did the skill get wrong the first time? Was it the instructions, or missing information?
- Did the plain-language request trigger it? If not, what in the `description` would you change?
- What else would you want a skill to do for you every time?
