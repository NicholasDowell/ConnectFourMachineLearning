

/**
	 * Square is an x,y coordinate along with an ID for which player owns the piece in that square
	 * the coordinate stores a position RELATIVE to the play in question
	 * this means that the coordinates CAN possibly be negative
	 * @author Nick
	 *
	 */
 	public class Square{
		int xChange;
		int yChange;
		int pieceValue; 
		/* a piece value is:
		 *  0 if the space is empty 
		 *  1 if the piece is an allied piece
		 *  2 if enemy piece
		 *  */
		
		/**
		 * constructor for square which sets its x,y, and piece value (piece value is whether it is ally or enemy or empty
		 */
		public Square(int newX, int newY, int newPieceValue){
			xChange = newX;
			yChange = newY;
			pieceValue = newPieceValue;
		}
		/**
		 * setter for xChange
		 */
		public void setX(int newX){
			xChange = newX;
		}
		/**
		 * setter for yChange
		 * @param newY
		 */
		public void setY(int newY){
			yChange = newY;
		}
		/**
		 * setter for xChange
		 * @param newPieceValue
		 */
		public void setPieceValue(int newPieceValue){
			pieceValue = newPieceValue;
		}
		/**
		 * getter for xChange
		 */
		public int getX(){
			return xChange;
		}
		/**
		 * getter for yChange
		 */
		public int getY(){
			return yChange;
		}
		/**
		 * getter for pieceValue
		 */
		public int getPieceValue(){
			return pieceValue;
		}
		/**
		 * checks to see if the x and y coordinates are the same as this
		 * THE PIECE VALUE COULD BE DIFFERENT AND IT WILL STILL RETURN YES
		 */
		public boolean equals(Square otherSquare){
			if (otherSquare.getX() == this.getX() && otherSquare.getY() == this.getY()){
				return true;
			}
			return false;
		}
		/**
		 * returns a string containing information about this square to the console
		 */
		public String toString(){
			String info = "";
			info += ", x: " + xChange;
			info += ", y: " + yChange;
			info += ", pieceValue: " + Integer.toString(pieceValue);
			return info;
		}
		/**
		 * updates the square so that it looks at the correct piece type depending on which player is evaluating it
		 * @param playerID
		 */
		public void correctPieceValue(int playerID){
			if (playerID == 2){
				switchPieceValue();
			}
		}
		/**
		 * switchPieceValue changes the pieceValue from 1 to 2, or from 2 to 1, depending on the starting value
		 */
		public void switchPieceValue(){
			if (pieceValue == 1){
				pieceValue = 2;
			}
			else if (pieceValue == 2){
				pieceValue = 1;
			}
		}
		/**
		 * returns true if this square holds the same pieceValue as the corresponding coordinates on the passed board
		 */
		public boolean matchesBoard(int column,int row, GameBoard theBoard){
			int boardValue = theBoard.getPiece(column, row);
			return boardValue == pieceValue;
			
		}
		
	}