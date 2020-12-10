For our Regular Expression program we implemented our program to use all the vocab as mentioned in the Regexp specification with the only
change we made being to use square brackets "[]" rather than parentheses "()" as this made it easier to enter into the console on different
platforms, the following is used:

Symbols:
* : zero or more times
? : zero or one times
| : alternation
[]: parentheses
\ : escaped characters

Grammar:
E -> T
E -> T E
T -> F
T -> F*
T -> F.
T -> F?
T -> F | T
F -> v
F -> (E)

We tried to implement a version of the parse method with improved error checking which would prevent inputs like "a|a" and "a*a" but
this ended up straying away from what demonstrated in the lectures to the point where things became a lot more complicated than they
needed to be, due to this we decided to not submit this version of the parsing code as we felt the changes made detracted from the way
the functionality of parsing was supposed to be.



In order to run the program the following call is used to run both the REcompile and REsearch

java REcompile "<Expression>" | REsearch <Filename>

(Please note the " " around expression are required otherwise the expression will not parse for certian symbols)
