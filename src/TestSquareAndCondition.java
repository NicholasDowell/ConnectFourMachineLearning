/**
 * tests the squre and condition classes
 * @author Nick
 *
 */
public class TestSquareAndCondition {
	public static void main(String[] args) {
		
		
		
		
		Condition c = new Condition(1);
		for (int i = 0; i < 3; i ++){
			c.addRandomSquare();
		}
		System.out.println(c.toString());
		
		System.out.println("  BBBB   }E{FSDOFSDFhiu");
		for (int i = 0; i < 10; i ++){
			c.changeRandomPieceValue();
		}
		
		System.out.println(c.toString());
		
		
		
		//THIS BLOCK TESTS THE ADDING SQUARE FUNCTION AND THE CHANGING COORDS FUNCTION
		/*
		 * Square s = new Square(1,1,1);
		 
		
		System.out.println(s.toString());
		
		Condition c = new Condition(1);
		
		
		
		
		
		for (int i = 0; i < 10; i ++){
			c.addRandomSquare();
		}
		
		System.out.println(c.toString());
		
		for (int i = 0; i < 10; i ++){
			c.changeRandomCoordinate();
		}
		System.out.println(" WEWEWEWEW");
		System.out.println(c.toString());
		*/
	}

}
