/**
 * 
 * @author JiaYin Chen #260690708, Yu Qi Liu #260687081
 * Autonomous Item of a World simulator
 */
public class Autonomous extends Item {
	
	/**
	 * Constructor for Autonomous
	 */
	public Autonomous(int x, int y){
		
		super("Autonomous", 'A', x, y);
	}
	
	/**
	 * Autonomous moves 1 cell in a random direction
	 */
	public void step(){

		int randomDir = (int)(Math.random()*4);
		
		int[] prevCoor = getLocation();
		int[] newCoor = prevCoor;
		
		//move by ONE cell
		// N = 0
		if (randomDir == 0){
			newCoor[1] += 1;
		}
		// W = 1 
		else if (randomDir == 1){
			newCoor[0] -= 1;
		}
		// S = 2
		else if (randomDir == 2){
			newCoor[1] -= 1;
		}
		// E = 3
		else{
			newCoor[0] += 1;
		}
		
		setLocation(newCoor);

	}

}
