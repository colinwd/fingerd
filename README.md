# README

This is a minimal implementation of a finger server as an exercise.

## Usage

To run the service, you can use the provided Gradle wrapper: `./gradlew run`
`^C` will stop the service.

To query the service you can telnet to it: `telnet localhost 8079`
Sending an empty query to it will list all users, or you can type a username to
get that user's listing.

## What features did you choose to implement and why?

Given the relatively constrained size of Finger compared to some other RFCs,
I implemented all features listed as 'MUST', with one exception.
From Section 2.2: "Any data transferred MUST be in ASCII format, with no
parity, and with lines ending in CRLF". I initially had my QueryParser strictly
enforcing the CRLF line ending, but ran into trouble when testing the service
via Telnet. The line ending characters were not being passed through to the
parser, so I chose to remove the strict checking.

The main features I did not implement are verbose mode (with the /W flag) and
{Q2} queries (host forwarding). I also did not include more information in a
single user query than is returned for a list query, or make the overall system
configurable by an administrator.

## If you had to do this project again, what would you do differently and why?

Overall I am pretty happy with the way things turned out. The code is fairly
clean, though of course there's always room for improvement there.

Given more time I would definitely test the query parsing more exhaustively -
I'm not totally certain it would stand up under heavier scrutiny. I would also
add more integration testing - right now the `while (true)` loop prevents
practical automated integration tests due to the inability to kill the server
thread after completing tests. Perhaps a flag passed to application launch
could configure whether it should run in daemon mode or accept one request and
shut down.

The response formatting could also be improved - It doesn't do any handling of
long fields and won't adjust columns accordingly.