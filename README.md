# ConnectFourMachineLearning
This project aims to create an intelligence which will learn how to find the winning strategy for connect four.

TODO: 
1. Implement saving function so that strategies can be saved/
2. Create a controller object which will allow user interaction for the evolution process.



A brief overview of the files contained in the repository:

Objects:
  GameBoard - A 2d array of integer which represents one Game of Connect Four TM HASBRO MILTON TOYS 1960-2017 DO NOT COPY .
  
  GameTable - An object which holds one GameBoard, as well as two Strategies(or players). 
              GameTable can advance the game by placing pieces and can detect boardstates which result in a win
 
  Strategy - Holds a list of conditions which it can analyze, 
              ending in a decision about which move to make on the board.
              
  Condition - A group of squares to look at on the board, and a weight to be considered if all squares match the board.
  
  Square    - A coordinate pair in (x,y) form which points to a spot on the grid relative to the move in question.
  
  TestDecisionFunction - A class which tests the code for the Strategy choosing where to play based on its conditions. 
                  Currently the strategies have no conditions, so play is random.
  
  TestGameTable - A class which tests the GameTable object for functionality.
  
  TestSquareAndCondition - Tests the Square and Condition objects to make sure their basic methods are working as intended.
  
  RandomPlayStrategy - [Currently not functioning] An extension of the Strategy class which plays randomly instead of calculatedly.  
