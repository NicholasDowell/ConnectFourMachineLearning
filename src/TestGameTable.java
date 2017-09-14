/**
 * TESTING GAME TABLE CALSSSCLASS
 * @author Nick
 *
 */
public class TestGameTable {

	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		GameTable table = new GameTable();
		
		table.playGame();
		table.printBoard();
		System.out.println("moves = " + table.getTurns());
		System.out.println("winner = " + table.getWinner());
		
		
		
		
		
	}
}
