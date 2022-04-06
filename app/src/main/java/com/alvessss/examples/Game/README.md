## Game class description

Here's the main logic of the program.

To start the game the *Start* button calls the 
getSequence method that returns an Integer array of
random numbers between 0 and 12(total number of squares)
and pass it to the *Screen* instance then the sequence is 
showed on the screen. As the sequence is showed the square's
listeners are enabled and the user can start play.

The Level class stores the level, score and max score of the
user. 3 callbacks are passed to the Screen to handle the level
and they are called when the user:
    *clicks on correct square* (increases the level according to the index of this square)
    *clicks on the wrong one* (sets the max score and reset the game and level data)
    *finishes the sequence* (increases the level and starts another game)

As the user finishes correctly a sequence the square's listeners are disabled for the
Game can show another sequence on the screen and so on.