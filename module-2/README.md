# Module 2: Plan a library system

**Time:** 30 minutes. **Output:** a plan. **No code in this module.**

## The exercise

Riverside Public Library wants to replace its shared spreadsheet with a real system.
Below is the product definition. Your job is to work with the AI to produce an
**implementation plan** for it, and save it as `module-2/PLAN.md`.

1. Read the product definition and the files in `data/`. Don't ask the AI anything yet.
2. Use **at least two** of the planning techniques from the slides to build the plan.
   Try them one at a time, so you can tell what each one added.
3. Write down what each technique caught that the others didn't.

A good plan has:

- Scope: what is in, what is out, and why
- The main parts of the system and how they talk to each other
- The data it stores
- The steps to build it, in order. Each step says what evidence supports it and how you'll know it worked
- A list of open questions you could not answer yourself

### Debrief

- Which technique changed the plan the most?
- What did the AI ask, or find, that you would have missed?
- What is still in your open-questions list? Who would you ask?

---

## Product definition: Riverside Library Manager

### Background

Riverside is a small public library. It lends books to members for free. Today everything is
kept in one shared spreadsheet, and staff phone members by hand when books are overdue.
See `data/current-stats.md` for how it runs today, and `data/books.csv` and
`data/members.csv` for a sample of the existing data.

### Who uses it

- **Librarians** work at the front desk and in the back office. They use a web application.
- **Members** don't log in. They receive emails.

### What it must do

**1. Inventory**

- The catalog has **titles** (ISBN, title, author, year) and **copies** of a title. Each copy
  has its own barcode and a condition: good, damaged or lost.
- A librarian can add copies of a title, and remove copies (for example, worn out or lost).
- A librarian can search by title, author or ISBN, and see how many copies are available.

**2. Members**

- A librarian registers members. A member has a type (adult, child or senior) and a membership
  that expires.
- Adults and seniors can borrow up to 10 items at once. Children can borrow up to 5.
- A member whose membership has expired can't borrow.

**3. Loans**

- A librarian checks a copy out to a member, and checks it back in when it returns.
- The standard loan period is 21 days.
- A loan can be renewed once, for another 14 days.
- A member who owes more than EUR 10 in fines can't borrow until they pay.

**4. Fines**

- A late return is charged EUR 0.20 per day, per item.
- A librarian can record that a fine was paid.
- A librarian can waive a fine.

**5. Notifications**

- Members get an email 3 days before a loan is due.
- Members get an email the day after a loan becomes overdue.
- The library should stop phoning members by hand.

### Not in the first version

- A members' app or website
- Payments by card
- Anything about the second branch. It comes later, but the first version shouldn't make it hard.

### Success

The library staff stop using the spreadsheet, and the 15 hours per week spent chasing
overdue books go away.
