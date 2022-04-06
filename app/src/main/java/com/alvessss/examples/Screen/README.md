## Screen class description

This class handles the visible elements of the game and also
receives a sequence and stores it.

It basically will:
    1 - Show the received sequence on the screen;
    3 - wait the user's click for
            paint this square Green and call onClickOnRightSquare if it's right or
            paint this square Red and call onClickOnWrongSquare if it's wrong or
            call onSequenceDone if the sequence is done.