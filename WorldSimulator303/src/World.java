import java.util.ArrayList;

public class World {

	private Item[][] world;
	private ArrayList<Autonomous> aList = new ArrayList<Autonomous>();
	private int rowNum;
	private int colNum;
	
	/**
	 * Constructor for World
	 * @param theRowNum number of rows in World
	 * @param theColNum number of columns in World
	 */
	public World(int theRowNum, int theColNum){
		world = new Item[theRowNum][theColNum];
		rowNum = theRowNum;
		colNum = theColNum;
	}
	
	
	/**
	 * Add one item to World
	 * @param item item with predetermined location to add on World
	 */
	public void add(Item item){
		
		// get item position
		int itemR = item.getRow();
		int itemC = item.getCol();
		
		// precondition
		if (itemR < 0 || itemR > rowNum || itemC < 0 || itemC > colNum){
			throw new IllegalArgumentException("Sorry, the World you are looking for does not exist.");
		}
		
		// if position on World isn't empty
		if(world[itemR][itemC] != null){
			throw new IllegalArgumentException("Sorry, this part of the World is full.");
		}
		
		// add item to world
		world[itemR][itemC] = item;
		
		// if item Autonomous, add to list
		if (item.getToken() == 'A'){
			aList.add((Autonomous)item);
		}
	}
	
	
	/**
	 * Print World to console
	 */
	public void display(){
		
		// loop through the array
		for(int i = 0; i < world.length; i++){
			for(int j = 0; j < world[i].length; j++){
				
				// empty cell
				if(world[i][j] == null){
					System.out.print(" _ ");
				}
				// print symbol
				else{
					System.out.print(" " + world[i][j].getToken() + " ");
				}
			}
			System.out.println("");
		}
		
		System.out.println("");
				 
	}
	
	
	/**
	 * Update all Autonomous and Moveable position. Triggered by all Autonomous stepping once
	 */
	public void step(){
		// for all Autonomous
		for(int i = 0; i < aList.size(); i++){
			// get Autonomous item
			Autonomous a = aList.get(i);
			
			// record original position
			int tempR = a.getRow();
			int tempC = a.getCol();
			
			// one step
			a.step();
			
			// record position after step
			int newR = a.getRow();
			int newC = a.getCol();
			
			// direction of step
			int[] direction = {newR - tempR, newC - tempC};
			System.out.println("Direction :" + direction[0] + "  " + direction[1]);
			
			// invalid new position, move back to old position
			boolean validPosition = isValidPosition(newR, newC);
			if (!validPosition){
				a.setRow(tempR);
				a.setCol(tempC);
			}
			//  new position is empty, stay there
			else if(world[newR][newC] == null){
				world[tempR][tempC] = null;
				world[newR][newC] = a;
			}
			// bumps into Immoveable, move back to old position
			else if(world[newR][newC].getToken() == 'I'){
				a.setRow(tempR);
				a.setCol(tempC);
			}
			// bump into Moveable/Autonomous
			else{		
				boolean success = stepConsequence(world[newR][newC], direction);
				// Moveable/Autonomous moved, stay at new position
				if(success){
					world[tempR][tempC] = null;
					world[newR][newC] = a;	
				}
				// Moveable/Autonomous failed to move, go back to original position
				else{
					a.setRow(tempR);
					a.setCol(tempC);
				}
			}	
		}
	}
	
	
	/**
	 * Check if position within World
	 * @param row row position to check
	 * @param col column position to check
	 * @return
	 */
	private boolean isValidPosition(int row, int col){
		// position doesn't exist
		if(row < 0 || row >= rowNum || col < 0 || col >= colNum){
			return false;
		}
		return true;
	}
	
	
	/**
	 * Move Item in direction
	 * @param row current row position of item
	 * @param col current column position of item
	 * @param direction direction to move towards
	 * @return is move successful
	 */
	private boolean stepConsequence(Item i, int[] dir){
		// validate can move Item
		if(i.getToken() != 'A' && i.getToken() != 'M'){
			return false;
		}
		
		// step in the direction of movement
		i.move(dir[0], dir[1]);
		
		// validate new position
		boolean isValid = isValidPosition(i.getRow(), i.getCol());
		// invalid new position, move back to old position
		if(!isValid){
			i.setRow(i.getRow() - dir[0]);
			i.setCol(i.getCol() - dir[1]);
			return false;
		}
		//  new position is empty, stay there
		else if(world[i.getRow()][i.getCol()] == null){
			world[i.getRow() - dir[0]][i.getCol() - dir[1]] = null;
			world[i.getRow()][i.getCol()] = i;
			return true;
		}
		// bumps into Immoveable, move back to old position
		else if(world[i.getRow()][i.getCol()].getToken() == 'I'){
			i.setRow(i.getRow() - dir[0]);
			i.setCol(i.getCol() - dir[1]);
			return false;
		}
		// bump into Moveable/Autonomous
		else{
			// move Item bumped into
			boolean success = stepConsequence(world[i.getRow()][i.getCol()], dir);
			// Item bumped into moved, move current Item
			if(success){
				world[i.getRow() - dir[0]][i.getCol() - dir[1]] = null;
				world[i.getRow()][i.getCol()] = i;				
				return true;
			}
			// Item bumped into failed to move, current Item go back to old position
			else{
				i.setRow(i.getRow() - dir[0]);
				i.setCol(i.getCol() - dir[1]);
				return false;
			}
		}
		
		
	}
	
	public static void main(String[] args){
		World w = new World(5,5);
		Autonomous A = new Autonomous(2,2);
		Autonomous A2 = new Autonomous(0,0);
		Moveable M = new Moveable(2,1);
		Moveable M2 = new Moveable(2,3);
		Moveable M3 = new Moveable(1,2);
		Moveable M4 = new Moveable(3,2);
		Immoveable I = new Immoveable(1,3);
		w.add(A);
		w.add(A2);
		w.add(M);
		w.add(M2);
		w.add(M3);
		w.add(M4);
		w.add(I);
		w.display();
		for(int i = 0; i < 20; i++){
			w.step();
			w.display();
		} 
		
		
	}
	
}
