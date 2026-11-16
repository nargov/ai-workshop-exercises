# Module 4: Build Wiki Pulse from examples

**Time:** 45 minutes. **Output:** working code, tests, and a small web UI. Written in `module-4/`.

## The exercise

Below is the product definition for **Wiki Pulse**, a live dashboard of Wikipedia edit activity.
You will build it using examples, and use subagents to build the parts in parallel.

1. Read the product definition and `data/event-format.md`. Look at a few lines of `data/sample-events.jsonl`.
2. With Claude, write **examples** in plain English for each part of the system. Save them as `module-4/EXAMPLES.md`.
   Each example is a concrete input and the exact output you expect. Cover the edge cases
   (empty windows, events exactly on a boundary, missing fields), not only the happy path.
3. Plan the tracks. Decide which parts are independent and which need to agree on an interface first.
4. Fan out subagents, one per track. Each turns its examples into a unit test, then into production code.
5. Wire the parts together and add the UI. Run it against `data/sample-events.jsonl`.

Java 17 and Maven. The UI is a web page served by your own program on `localhost`.
You don't need a framework. The JDK includes an HTTP server.

### Debrief

- Which examples did the AI get wrong when it had only the English description?
- Which examples would you have missed without writing them down first?
- Did the subagents' parts fit together the first time? What had to be agreed in advance?

---

## Product definition: Wiki Pulse

### Background

Wikimedia publishes a live feed of every edit made on Wikipedia and its sister sites. It sends
about 40 events per second, and it never stops. The team that runs a Wikipedia language edition
wants a small dashboard to see what is happening right now and to be told when something looks
unusual.

### The events

Each event is one change to one page. `data/event-format.md` describes the fields. For this
exercise, **all time comes from the event's own `timestamp` field**, never from the computer's
clock. A program that reads a replay of the feed must give the same answers as one reading it live.

The live feed is at:

```
https://stream.wikimedia.org/v2/stream/recentchange
```

It needs no account or key. It is a server-sent events stream: one long-lived HTTP GET, and each
event arrives as a line starting with `data: ` followed by JSON. Send a `User-Agent` header that
says who you are. To look at it:

```bash
curl -N -H 'User-Agent: workshop-exercise' https://stream.wikimedia.org/v2/stream/recentchange
```

The feed is not clean. Sometimes an event is cut off, or a field is missing or has the wrong type.

### What it must do

**1. Reading events**

- Wiki Pulse can read events from the live feed or from a file of saved events
  (`data/sample-events.jsonl`). The rest of the system must not care which.
- An event that can't be read, or has no `timestamp` or no `wiki`, is skipped. Wiki Pulse counts
  how many it skipped.
- A file can be replayed as fast as possible (for tests) or at a chosen speed, such as 10 times real time.
- If the live connection drops, Wiki Pulse reconnects.

**2. Classifying events**

Each valid event gets:

- A **kind**: `NEW_PAGE` (type `new`), `EDIT` (type `edit`), or `OTHER` (anything else).
- An **actor**: `BOT` if the `bot` field is true, otherwise `HUMAN`.
- A **size change** (for edits and new pages): the new length minus the old length. A new page has
  no old length, so it counts as zero.
- It is a **big change** if the size change is 5,000 characters or more in either direction.

Only `NEW_PAGE` and `EDIT` events are called **edits** in the rest of this document.

**3. Metrics**

The dashboard shows these. "Now" is the timestamp of the newest event seen so far.

- **Event rate**: valid events per second over the last 10 seconds.
- **Top 5 wikis** by number of edits in the last 60 seconds.
- **Top 5 pages** by number of edits in the last 60 seconds. A page is identified by its wiki and title.
- **Bot share**: the percentage of all valid events in the last 60 seconds that came from bots.
- **Biggest change**: the page with the largest size change (in either direction) in the last 60 seconds.

A window of N seconds covers the N seconds ending at "now". An event exactly N seconds old is outside it.
Ties in a top-5 list are ordered alphabetically. Events can arrive slightly out of order.

**4. Alerts**

- **Burst**: a human user makes 60 or more edits within 30 seconds. Bots never trigger this.
  The same user can trigger it again only after 60 seconds have passed.
- **Hot page**: a page gets 3 or more edits from at least 2 different users within 60 seconds.
  The same page can trigger it again only after 60 seconds have passed.

An alert says what happened, who or what page, and the time.

**5. The report**

- Every 30 seconds of event time, and once when the input ends, Wiki Pulse prints a short text
  summary of the current metrics and any alerts since the last report.

**6. The UI**

- A web page, updating live, with: the event rate, the top 5 wikis, the top 5 pages, the bot share,
  the biggest change, and a list of alerts.
- It also shows how many events were skipped, and whether it is reading from the sample file or the live feed.
- The person running it can choose the source and the replay speed.

### Not in the first version

- Saving anything to a database
- Logging in, or more than one person using the UI at once
- Anything that isn't about edits, such as page views or reading a page's text

### Success

A maintainer opens the page, sees a live view of the edits, and is told
when a user or a page is behaving unusually.
