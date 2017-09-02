/**
 * holds a 2 dimensional array of integers representing a Connect Four board
 * 
 * @author Nick
 *
 */
public class GameBoard {

	private final int WIDTH = 7;
	private final int HEIGHT = 6;
	
	//the first array is the COLUMNS, horizontally extending from each ROW starting point. X value
	//the second array is the array of ROWS. this is a vertical stack of ROWS Y value
	//it goes board [x][y] with [0][0] being the bottom left space.
	private int[][] grid;
	//  board spaces are filled as follows:
	//  0: empty space
	//  1: player 1's piece  / black piece
	//  2: player 2's piece  / red piece
	
	/**
	 * constructs a board with an empty board the right width and hreight
	 */
	public GameBoard(){
		this.grid = new int[WIDTH][HEIGHT];
		for(int i = 0; i < WIDTH; i ++){
			for (int ii = 0; ii < HEIGHT; ii ++){
				grid[i][ii] = 0;
			}
		}
	}
	/**
	 * sets a space equal to a certain integer value
	 */
	public void setSpace(int column, int row, int playerID){
		System.out.println("setting piece at " + column + " , " + row);
		grid[column][row] = playerID;
		
	}
	/**
	 * resets the board to all empty spaces
	 */
	public void reset(){
		for (int columnIndex = 0; columnIndex < WIDTH; columnIndex ++){
			for(int rowIndex = 0; rowIndex < HEIGHT; rowIndex ++){
				grid[columnIndex][rowIndex] = 0;
			}
		}
		
	}
	/**
	 * getter for board height
	 */
	public int getHeight(){
		return HEIGHT;
	}
	/**
	 * getter for board Width
	 */
	public int getWidth(){
		return WIDTH;
	}
	/**
	 * getter for individual space
	 * returns 0 if empty
	 * returns 1 if space holds piece belonging to player 1
	 * returns 2 if piece belongs to player 2
	 */
	public int getPiece(int column, int row){
		if (column < 0 || column >= WIDTH){
			return 0;
		}
		if (row < 0 || row >= HEIGHT){
			return 0;
		}
		return grid[column][row];
	}
}
