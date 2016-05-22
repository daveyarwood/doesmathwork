# does math work?

## Overview

This is a web application that checks to see if math still works in a number of languages. (If math ever stops working, we'll be in deep trouble.)

> As far as this app is concerned, if 1 + 1 == 2, then math still works.

## Setup

### Server-side languages

A "does math work?" program returns 0 if `1 + 1 == 2`, or non-zero otherwise.

The web app will test a language on demand by hitting an AJAX endpoint. When the server gets the request, it will shell out and run the program written in that language that checks if math still works. Doing this should be as simple as:

```
cd langs/$LANGUAGE && make run
```

The server will return a 200 response either way, because even if math stops working, we still need to know if the request was successful or not. There could be some non-math-related reason for the server failing to respond with confirmation or denial that math works.

The server response will be a JSON object that looks like this:

```json
{
    "result": true,
    "output": "[c] Checking to see if math works...\n[c] Math works.\n"
}
```

...where `result` indicates whether or not math works, and `output` is the printed output of the program.

### Client-side languages

TBD.

## License

Copyright © 2016 Dave Yarwood

Distributed under the Eclipse Public License version 1.0.
