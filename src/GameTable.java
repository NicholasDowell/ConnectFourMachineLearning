import java.net.StandardSocketOptions;
import java.util.ArrayList;

/**
 * game table holds a GameBoard and two Strategies, defining the way each player makes decisions
 * GameTable can run a game or reset the board
 * @author Nick
 *
 */
public class GameTable {
	private Strategy playerOne;
	private Strategy playerTwo;
	private GameBoard board;
	private int activePlayer = 1;
	private int winner;
	private int turns = 0;
	/**
	 * constructs a new GameTable with an empty board
	 */
	public GameTable(){
		board = new GameBoard();
		playerOne = new Strategy(1);
		playerTwo = new Strategy(2);
	}
	/**
	 * new game function
	 */
	/**
	 * calls the Strategy whos turn it is to make a decision about where to play.
	 * returns the column where the player wants to play
	 */
	public int choosePlay(){
		System.out.println("Choosing new play" + "    - number of available columns: " + openColumnsArray().length);
		Strategy currentPlayer = getActivePlayer();
		int[] openColumns = openColumnsArray();
		double[] weightSums = new double[openColumns.length];
		
		// The following loop prints out the contents of the open columns array
		System.out.println("OPEN COLUMNS length" + openColumns.length);
		for (int i = 0; i < openColumns.length; i ++){
			
			System.out.print(openColumns[i]);
			
		}
		System.out.println("");
		//loops through each possible play, adding up its weight
		for (int columnIndex = 0; columnIndex < openColumns.length; columnIndex ++){
			GameBoard tempBoard = board;
			int column = openColumns[columnIndex];
			int row = highestPiece(column) + 1;
			//placePawn(column,tempBoard);
			weightSums[columnIndex] = currentPlayer.weigh(column,row,tempBoard);		
		}
		// PRints out the weights of all open columns
		System.out.println( "V Weight Sums V");
		for (int i = 0; i < weightSums.length; i ++){
			System.out.println(weightSums[i]);
		}
		System.out.println("^       ^");
		
		double highestWeight = 0;
		//finds the column with the highest weight
		for (int weightIndex = 0; weightIndex < weightSums.length; weightIndex ++){
			if (weightSums[weightIndex] > highestWeight){
				highestWeight = weightSums[weightIndex];
			}
		}
		//for each column with the highest value, add the COLUMN NUMBER to a separate array
		//then we can select one play with the highest weight at random
		ArrayList<Integer> highestWeights = new ArrayList<Integer>();
		
		for (int weightIndex = 0; weightIndex < weightSums.length; weightIndex ++){
			if (weightSums[weightIndex] == highestWeight){
				System.out.println("Column " + openColumns[weightIndex] + " is highest");
				highestWeights.add(openColumns[weightIndex]);
			}
		}
		// This loop is test for making sure the highestWeights array works
		System.out.println("ColumnsWithHighWeight . length: " + highestWeights.size());
		for (int i = 0; i < highestWeights.size(); i ++){
			System.out.println(highestWeights.get(i));
		}
		
		int finalPlay = 0;
		System.out.println("WEIGHTSSIZE" + highestWeights.size());
		int randomHighestWeightColumn = (int)(Math.random() * highestWeights.size());
		System.out.println("Ran Index"  + randomHighestWeightColumn);
		System.out.println("opencolumns[randomhihg] " + openColumns[randomHighestWeightColumn]);
		finalPlay = highestWeights.get(randomHighestWeightColumn);
		
		
		System.out.println("Final Play : " + finalPlay);
		return finalPlay;
	}
	/**
	 * Moves the game forward one turn
	 */
	public void advanceGame(){
		int column = 0;
		column = choosePlay();
		placePawn(column, board);
		turns ++;
		if (connectsFour(column, highestPiece(column))){
			winner = activePlayer;
		}
		changeActivePlayer();	
		
	}
	/**
	 * Game running function - running this function will change the int winner to 1 or 2 if a player won, or zero if tie.
	 */
	public void playGame(){
		int turns = 0;
		int spacesOnBoard = board.getHeight() * board.getWidth();
		while (winner == 0 && turns <= spacesOnBoard){
			advanceGame();
		}
	}
	/**
	 * returns the number cooresponding to the winner of the game
	 */
	public int getWinner(){
		return winner;
	}
	/**
	 * returns the row number that contains the most recent play in the passed column
	 */
	public int highestPiece(int column){
		int row = board.getHeight();
		while (board.getPiece(column, row) == 0 && row >=0){
			row --;
		}
		return row;
	}
	/**
	 * returns true of there is at least one space available to play in the column
	 */
	public boolean columnIsOpen(int column){
		if (highestPiece(column) < board.getHeight()-1){
			return true;
		}
		return false;
	}
	/**
	 * returns an array containing all of the columns with at least once space available to play in
	 */
	public int[] openColumnsArray(){
		
		int width = board.getWidth();
		
		ArrayList<Integer> columnsArray = new ArrayList<Integer>();
		int numberOfOpenColumns = 0;
		
		System.out.println("The width : " + width);
		for (int columnNumber = 0; columnNumber < width; columnNumber ++){
			boolean openColumn = columnIsOpen(columnNumber);
			System.out.println(" Column " + columnNumber + "is Open: " + openColumn);
			if (openColumn){
				columnsArray.add(columnNumber);
				numberOfOpenColumns++;
				System.out.println("loop" + numberOfOpenColumns);
			}
		}
		System.out.println("number of open columns: " + numberOfOpenColumns);
		
		int[] returnArray = new int[numberOfOpenColumns];
		for (int i = 0; i < numberOfOpenColumns; i ++){
			
			returnArray[i] = columnsArray.get(i);
		}
		System.out.println("OCA returning array of length : " + returnArray.length);
		return returnArray;
	}
	
	/**
	 * prints a single square 
	 */
	public void printSquare(int column, int row){
		int squareValue = board.getPiece(column,row);
		System.out.print("|");
		switch (squareValue) {
		case 0: System.out.print(" _ ");
				break;
		case 1: System.out.print(" X ");
				break;
		case 2: System.out.print(" O ");
				break;
		}
		System.out.print("");
		
		
	}
	/**
	 * prints one row of the board
	 */
	public void printRow(int row){
		for (int widthIndex = 0; widthIndex < board.getWidth(); widthIndex ++){
			printSquare(widthIndex,row);
		}
	}
	
	/**
	 * print all rows of the board, this creates a complete Visualization of the current boardstate
	 */
	public void printBoard(){
		for (int heightIndex = board.getHeight()-1; heightIndex >= 0; heightIndex --){
			printRow(heightIndex);
			System.out.println("|");
			
		}
	}
	/**
	 * the active player makes a move in the specified column
	 */
	public void placePawn(int column,GameBoard theBoard){
		int row = highestPiece(column) + 1;
		if (row < theBoard.getHeight()){
			theBoard.setSpace(column, row, activePlayer);
		}
		/*for (int heightIndex = 0; heightIndex < board.getHeight(); heightIndex ++){
			if (board.getPiece(column, heightIndex) == 0){
				board.setSpace(column, heightIndex, activePlayer);
				System.out.println("Pawn placed in square: " + column + "," + heightIndex);
				return;
			}
		}*/
		
	}
	/**
	 * changes the active player between player 1 and player 2
	 */
	public void changeActivePlayer(){
		if (activePlayer != 1){
			activePlayer = 1;
		}
		else activePlayer = 2;
	}
	/**
	 * checks to see if a set of coordinates contains a piece that is part of a win
	 * returns true if there is a win
	 */
	public boolean connectsFour(int column, int row){
	
		if (horizontalWin(column,row) 
			|| verticalWin(column,row) 
			|| diagonalUpWin(column,row) 
			|| diagonalDownWin(column,row)
			)
		{
			return true;
		}
		return false;
	}
	/**
	 * returns true if the passed coordinates contain a piece which is part of a diagonal Down win
	 */
	public boolean diagonalDownWin(int column, int row){
		if (board.getPiece(column, row)==0){
			return false;
		}
		int distance = 0;
		int piecesConnected = 0;
		int focusRow = row;
		int focusColumn = column;
		while (piecesMatch(column,row, focusColumn+1, focusRow-1) && distance < 4){
			System.out.println("LOOKING AT coords : " + column + "," + focusRow + "returns yes");
			piecesConnected ++;
			distance ++;
			focusRow --;
			focusColumn++;
		}
		distance = 0;
		focusRow = row;
		focusColumn = column;
		while (piecesMatch(column,row,focusColumn-1,focusRow+1) && distance <4){
			System.out.println("LOOKING AT coords : " + column + "," + focusRow + "returns yes");
			piecesConnected ++;
			distance ++;
			focusRow ++;
			focusColumn--;
		}
		if (piecesConnected >= 3){
			System.out.println("diagonal down win");
			return true;
		}
		return false;
	}
	/**
	 * returns true if the passed coordinates contain a piece which is part of a diagonal Up win
	 */
	public boolean diagonalUpWin(int column, int row){
		if (board.getPiece(column, row)==0){
			return false;
		}
		int distance = 0;
		int piecesConnected = 0;
		int focusRow = row;
		int focusColumn = column;
		while (piecesMatch(column,row, focusColumn+1, focusRow+1) && distance < 4){
			System.out.println("LOOKING AT coords : " + column + "," + focusRow + "returns yes");
			piecesConnected ++;
			distance ++;
			focusRow ++;
			focusColumn++;
		}
		distance = 0;
		focusRow = row;
		focusColumn = column;
		while (piecesMatch(column,row,focusColumn-1,focusRow-1) && distance <4){
			System.out.println("LOOKING AT coords : " + column + "," + focusRow + "returns yes");
			piecesConnected ++;
			distance ++;
			focusRow --;
			focusColumn--;
		}
		if (piecesConnected >= 3){
			System.out.println("diagonal up win");
			return true;
		}
		return false;
	}
	/**
	 * returns true if the coordinates passed contain a piece which is part of a vertical win
	 */
	public boolean verticalWin(int column,int row){
		if (board.getPiece(column, row)==0){
			return false;
		}
		int distance = 0;
		int piecesConnected = 0;
		int focusRow = row;
		while (piecesMatch(column,row, column, focusRow+1) && distance < 4){
			System.out.println("LOOKING AT coords : " + column + "," + focusRow + "returns yes");
			piecesConnected ++;
			distance ++;
			focusRow ++;
		}
		distance = 0;
		focusRow = row;
		while (piecesMatch(column,row,column,focusRow-1) && distance <4){
			System.out.println("LOOKING AT coords : " + column + "," + focusRow + "returns yes");
			piecesConnected ++;
			distance ++;
			focusRow --;
		}
		if (piecesConnected >= 3){
			System.out.println("vertical win");
			return true;
		}
		return false;
	}
	/**
	 * returns true if the square in question is part of a horizontal win.
	 */
	public boolean horizontalWin(int column, int row){
	
		int playedPiece = board.getPiece(column,row);
		if (playedPiece == 0){
			return false;
		}
		int distance = 0;
		int piecesConnected = 0;
		int focusColumn = column;
		//first checks to the right
		while (piecesMatch(column,row,focusColumn+1,row) && distance < 4){
			piecesConnected ++;
			focusColumn ++;
			distance ++;
		}
		distance = 0;
		focusColumn = column;
		//then to the left
		while (piecesMatch(column,row,focusColumn-1,row) && distance < 4){
			piecesConnected ++;
			focusColumn --;
			distance ++;
		}
		if (piecesConnected >= 3){
			System.out.println("horizontal win");
			return true;
		}
		return false;
	}
	/**
	 * returns true if two spaces contain the same game piece. 
	 * pass this function two sets of coordinates (x1,y1,x2,y2)
	 * EMPTY SPACE ANYWHERE RETURNS FALSE
	 */
	public boolean piecesMatch(int columnOne, int rowOne, int columnTwo, int rowTwo){
		if (board.getPiece(columnOne, rowOne) == board.getPiece(columnTwo, rowTwo)){
			return true;
		}
		return false;
	}
	/**
	 * returns the strategy corresponding to which player is active
	 */
	public Strategy getActivePlayer(){
		if (activePlayer == 1){
			return playerOne;
		}
		else return playerTwo;
	}
	/**
	 * returns the board
	 */
	public GameBoard getBoard(){
		return board;
	}
	/**
	 * returns the first player's strategy
	 */
	public Strategy getPlayerOneStrategy(){
		return playerOne;
	}
	/**
	 * returns the second players strategy
	 */
	public Strategy getPlayerTwoStrategy(){
		return playerTwo;
	}
	/**
	 * gets number of passed turns
	 */
	public int getTurns(){
		return turns;
	}
	
	

}
