import java.util.ArrayList;

/**
 * a Strategy holds a list of conditions to be analyzed
 * it can pick a column to play in
 * @author Nick
 *
 */
public class Strategy {
	public ArrayList<Condition> conditions;
	public int playerID;
	private int generation; //the generation may be useful at some point
	
	//maybe store a number of wins? or a win rate? as an integer for testing purposes.
	
	/**
	 * constructs a Strategy object with a specific playerID
	 */
	public Strategy(int newPlayerID){
		playerID = newPlayerID;
		conditions = new ArrayList<Condition>();
		updateConditionPieceValues();
	}
	/**
	 * returns the sum of the weights of All conditions given a specific boardstate, x coordinate, y coordinate
	 * THE strategy knows which player it is already ^
	 */
	public double weigh(int column, int row, GameBoard theBoard){
		double sumOfWeights = 0;
		for (int conditionIndex = 0; conditionIndex < conditions.size(); conditionIndex++){
			 sumOfWeights += conditions.get(conditionIndex).weigh(column,row,theBoard);
			 
		}
		System.out.println("Strategy summing a weight of: " + sumOfWeights +" at index " + column + row);
		return sumOfWeights;
	}
	/**
	 * updates all the conditions to match this player's ID
	 */
	public void updateConditionPieceValues(){
		
		for (int conditionsIndex = 0; conditionsIndex < conditions.size(); conditionsIndex ++){
			System.out.println("updating " + conditionsIndex);
			conditions.get(conditionsIndex).updatePieceValues(playerID);
		}
	}
	/**
	 * adds a new condition to this strategy
	 */
	public void addCondition(Condition newCondition){
		conditions.add(newCondition);
	}
	/**
	 * adds a random condition to the list
	 */
	public void addRandomCondition(){
		conditions.add(new Condition());
	}
	
	
	

}
