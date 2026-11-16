# Event format

`sample-events.jsonl` has one JSON object per line. It is a recording of about two minutes of
Wikimedia's `recentchange` feed, trimmed to the fields below. Usernames were replaced with
`user-NNNN` and `bot-NNN`, and edit comments were removed.

The live feed sends the same fields, plus more that you can ignore. It is at
`https://stream.wikimedia.org/v2/stream/recentchange` (server-sent events: each event is a line
starting with `data: ` followed by this JSON).

| Field | Type | Meaning |
|---|---|---|
| `meta.id` | string | Unique id of the event |
| `meta.dt` | string | Time the event was published, ISO 8601 |
| `meta.domain` | string | Site, for example `en.wikipedia.org` |
| `type` | string | `edit`, `new`, `log` or `categorize` |
| `namespace` | number | Kind of page. `0` is a normal article |
| `title` | string | Page title |
| `timestamp` | number | When the change happened, Unix seconds |
| `user` | string | Who made the change |
| `bot` | boolean | True if the editor is a registered bot |
| `wiki` | string | Wiki id, for example `enwiki` |
| `minor` | boolean | True for a minor edit. Not present on every event |
| `length.old`, `length.new` | number | Page size in characters, before and after. Not present on `log` or `categorize`. A `new` event has no `length.old` |
