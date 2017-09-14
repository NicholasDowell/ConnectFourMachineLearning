/**
 * this class will test the methods which define the way the AI makes decisions
 * @author Nick
 *
 */
public class TestDecisionFunction {
	
	public static void main(String[] args) {
		
		//create a game board
		GameTable table = new GameTable();
		
		Condition c = new Condition();
		c.setOneSquare(0,-1,1,1);
		
		Condition n = new Condition();
		n.setOneSquare(1,0, 1,2);
		table.getPlayerOneStrategy().addCondition(n);
		
		table.getPlayerOneStrategy().addCondition(c);
		
		for (int i = 0; i < 10; i ++){
			table.placePawn(table.choosePlay(), table.getBoard());
			table.printBoard();
			
		}
	}

}
