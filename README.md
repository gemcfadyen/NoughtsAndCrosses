NoughtsAndCrosses
=================

This project is running on the continuous build server drone.io.
The current [![Build Status](https://drone.io/github.com/gemcfadyen/NoughtsAndCrosses/status.png)](https://drone.io/github.com/gemcfadyen/NoughtsAndCrosses/latest)

Initial Idea
============

Kata to implement a game of noughts and crosses

Following on from a LSCC (London Software Craftsmanship Community) hands on session where we had to implement solutions to the tic-tac-toe game, upload them to a server and play against each other, I decided to implement a game of noughts and crosses such that one player is you, the human, whilst the other is your program.


Approach
========

1. The Game algorithm was put in place. TDD was used to allow the dependant objects to be formed, and Mockito was used to mock out all the dependency interactions.
2. I started fleshing out the Player - deciding to turn the Player into an interface so that two types of player could exist - the AutomatedPlayer (who is using an algorithm to decide the next move) and a HumanPlayer who types their input in from the command line.
3. I debated as to whether the algorithm for the AutomatedPlayer should live in the Grid class or the AutomatedPlayer. In the end I decided it should live in the AutomatedPlayer, afterall each player should devise their own strategy. I was keen however on keeping the Grid akin to a First class collection, therefore I envisage lots of methods being added to the Grid to allow the different players to query its state.
4. Testing the simulation of a human entering their input was something I was initially concerned about however by using a StringReader and Writer, I was able to control output in the unit tests.
5. Initially implemented with a fixed sized grid (3 x 3) after several refactoring stages the code lent itself to allow a grid of any size, hence this became part of the game - the player can specify what size grid they want to play with.
6. Input is validated, if a user enters the wrong sort of input then they will be reprompted until a valid entry is made.

Algorithm
=========
* Each time a move is made, the resulting grid is evaluated to see if there is a winning row. If there is, the game ends. 
* When the AutomatedPlayer takes a turn, there is a prioritsed set of moves that are attempted.
* The automated player first looks for a winning move to make.
* If no such move exists they look for a row where the opponent could win on their next go. 
* Failing that they go for centre, or corner or any free space depending on availability.

Results
=======
* If you play against the computer on a 3 x 3 grid its unlikely you will win. In fact is unlikely anyone will win, the human player tends to end up in defence mode and the game is a draw.
* I have managed to win using a 4 x 4 grid... so Good Luck!
