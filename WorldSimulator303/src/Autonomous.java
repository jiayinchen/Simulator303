
public class Autonomous extends Item{

	/**
	 * Constructor for Autonomous
	 */
	public Autonomous(){
		super("Autonomous", 'A', 0, 0);
	}
	
	/**
	 * Autonomous pick a random direction and moves one cell to that direction
	 */
	public void step(){
		int[] direction = randomDirection();
		move(direction[0], direction[1]);
	}
	
	/**
	 * Generate a random direction (N, S, W, E)
	 * @return change in (x,y) to the direction
	 */
	private int[] randomDirection(){
		int randomDir = (int)(Math.random()*4);
		int[] direction;
		
		// N = 0
		if (randomDir == 0){
			direction = new int[]{0, 1};
		}
		// W = 1 
		else if (randomDir == 1){
			direction = new int[]{-1, 0};
		}
		// S = 2
		else if (randomDir == 2){
			direction = new int[]{0, -1};
		}
		// E = 3
		else{
			direction =  new int[]{1, 0};
		}
		
		return direction;
	}
	
}
