
public class Autonomous extends Item{

	
	public Autonomous(int row, int col){
		super("Autonomous", 'A', row, col);
	}
	
	/**
	 * Autonomous pick a random direction and moves one cell to that direction
	 */
	public void step(){
		int[] direction = randomDirection();
		boolean moved = move(direction[0], direction[1]);
		while (!moved){
			direction = randomDirection();
			moved = move(direction[0], direction[1]);
		}
	}
	
	/**
	 * Generate a random direction (N, S, W, E)
	 * @return row or column shift towards the random direction
	 */
	private int[] randomDirection(){
		int randomDir = (int)(Math.random()*4);
		int[] direction;
		
		// N = 0
		if (randomDir == 0){
			direction = new int[]{-1, 0};
		}
		// W = 1 
		else if (randomDir == 1){
			direction = new int[]{0, -1};
		}
		// S = 2
		else if (randomDir == 2){
			direction = new int[]{1, 0};
		}
		// E = 3
		else{
			direction =  new int[]{0, 1};
		}
		
		return direction;
	}
	
}
