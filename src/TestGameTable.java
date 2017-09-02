/**
 * TESTING GAME TABLE CALSSSCLASS
 * @author Nick
 *
 */
public class TestGameTable {

	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		GameTable table = new GameTable();
		
		
		
		for (int i = 0; i < 20; i ++){
			table.advanceGame();
			table.printBoard();
			
			System.out.println("Winner " + table.getWinner());
			System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
			
		}
		
		
		
		
		
	}
}
