/**
 * a condition will store a list of points on the board, and for each one, whether an ally or enemy or empty square must be there
 * positions are stored as relative to the square in question.
 * 
 * @author Nick
 *
 */
public class Condition {
	private Square[] squares = new Square[41];
	int numberOfSquares = 0;
	private int weight;
	
	/**
	 * empty constructor
	 */
	public Condition(int startingWeight){
		weight = startingWeight;	
	}
	/**
	 * Creates a randomly generated condition
	 */
	public Condition(){
		//RANDOM PLEASE UPDATEE SO THAT IT WORKS
	}
	/**
	 * getter for weight
	 * 
	 */
	public int getWeight(){
		return weight;
	}
	/**
	 * getter for number of squares
	 */
	public int getNumberOfSquares(){
		return numberOfSquares;
	}
	/**
	 * getter for squares
	 */
	public Square[] getSquares(){
		return squares;
	}
	/**
	 * adds a new Square to the array of squares.
	 * the new square will have random coordinates.
	 *  the new square will not have the same coordinates as any of the existing ones within this condition
	 * @author Nick
	 *
	 */
	public void addRandomSquare(){
		//until works:
		// 1: generate 2 random numbers.
		// 2: the first is the x value for the 
		// 3. the second is the y Value
		Square newSquare = new Square(0,0,0);
		
		boolean done = false;
		while (!done){
			done = true;
			int x = (int)(Math.random()*7);
			int y = (int)(Math.random()*6);
			int piece = (int)(Math.random()*3);
			newSquare = new Square(x,y,piece);
			for (int squareIndex = 0; squareIndex < numberOfSquares; squareIndex ++){
				if (squares[squareIndex].equals(newSquare)){
					done = false;
				}
			}
		}
		squares[numberOfSquares] = newSquare;
		numberOfSquares ++;
		
	}
	
	/**
	 * Picks a random square and changes either its x or y value by either +1 or -1
	 * square, coordinate, and direction are all chosen at random.
	 */
	public void changeRandomCoordinate(){
			//get a random square
		Square theSquare = getRandomSquare();
		// pick 1 or 2
		int coordinatePicker = (int)((Math.random()* 2) + 1); //this sets 1 for x and 2 for y
		// 1 means change x coord, 2 means change y coord
		int directionPicker = (int)(Math.random()); //this sets 0 for negative and 1 for positive
		//pick positive or negative
		if (coordinatePicker == 1){
			if (directionPicker == 0){
				theSquare.setX(theSquare.getX()-1);//minus 1
			}
			else if (directionPicker == 1){
				theSquare.setX(theSquare.getX()+1);
			}
		}
		else if (coordinatePicker == 2){
			if (directionPicker == 0){
				theSquare.setY(theSquare.getY()-1);
			}
			else if(directionPicker == 1){
				theSquare.setY(theSquare.getY()+1);
			}
		}
		
	}
	/**
	 * Removes a random Square from the list
	 */
	public void removeRandomSquare(){
		int removedIndex = (int)(Math.random() * numberOfSquares);
		for (int arrayIndex = removedIndex; arrayIndex < numberOfSquares-1; arrayIndex ++){
			squares[arrayIndex] = squares[arrayIndex + 1];
		}
		numberOfSquares --;
		//this will result in overwriting only the square at the randomly chosen index
	}
	/**
	 * changes the weight of this condition at random 
	 * It either goes up by 1 or down by 1
	 */
	public void changeRandomWeight(){
		int direction = (int)(Math.random() * 2);
		//pick negative or positive
		
		if (direction == 0){
			weight --;
		}
		else {
			weight ++;
		}
	}
	/**
	 * randomly reassigns the piece value that a random square is evaluating
	 */
	public void changeRandomPieceValue(){
		int newValue = (int)(Math.random() * 3);
		
		getRandomSquare().setPieceValue(newValue);
		//pick 0,1,or 2 MATH * 3 please
		//assign the piece value of a random square to that number
	}
	/**
	 * returns a random square from this condition's squares list
	 */
	public Square getRandomSquare(){
		int index = (int)(Math.random()* numberOfSquares);
		return squares[index];
	}
	
	
	/**
	 * prints out information about this condition to the console
	 */
	public String toString(){
		String info = "";
		info += ", Size: " + numberOfSquares;
		info += ", Weight : " + weight;
		for (int squareIndex = 0; squareIndex < numberOfSquares; squareIndex ++){
			info += squares[squareIndex].toString();
			info += "\n";
		}
		return info;
	}
	/**
	 * depending on which player is active, the piece values are updated to reflect that
	 */
	public void updatePieceValues(int playerID){
		for (int squareIndex = 0; squareIndex < numberOfSquares; squareIndex ++){
			squares[squareIndex].correctPieceValue(playerID);
		}
	}
	/**
	 * evaluates each square in this condition. If each square matches the passed board, returns the weight of this condition
	 * if it does not match, returns zero
	 */
	public int weigh(int column, int row, GameBoard theBoard){
		
		for (int squareIndex = 0; squareIndex < numberOfSquares; squareIndex ++){
			System.out.println("Examining square " + squareIndex);
			if (!squares[squareIndex].matchesBoard(column, row, theBoard)){
				return 0;
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Condition is triggered at x" + column + "y" + row + " weight of" + weight);
		return weight;
	}
	/**
	 * TESTING FUNCTION
	 * this function sets the next available index of the square array to a new X,Y value
	 * 
	 */
	public void setOneSquare(int x, int y, int p, int w){
		Square s = new Square(x,y,p);
		squares[numberOfSquares] = s;
		numberOfSquares++;
		weight = w;
	}
	
}
