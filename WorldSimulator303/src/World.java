import java.util.ArrayList;

public class World {

	private Item[][] world;
	private ArrayList<Autonomous> aList = new ArrayList<Autonomous>();
	private int xWorld;
	private int yWorld;
	
	/**
	 * Constructor for World
	 * @param x number of columns in World
	 * @param y number of rows in World
	 */
	public World(int x, int y){
		world = new Item[x][y];
		xWorld = x;
		yWorld = y;
	}
	
	/**
	 * Add an Item to cell (x,y) of World
	 * @param item Item to add
	 * @param x x coordinate of cell
	 * @param y y coordinate of cell
	 */
	public void add(Item item, int x, int y){
		// precondition
		if ( x < 0 || x > xWorld || y < 0 || y > yWorld){
			throw new IllegalArgumentException("Sorry, the World you are looking for does not exist.");
		}
		
		if(world[x][y] != null){
			throw new IllegalArgumentException("Sorry, this part of the World is full.");
		}
		
		// update item's coordinate
		item.setXCoord(x);
		item.setYCoord(y);
		
		// add item to world
		world[x][y] = item;
		
		// if Autonomous, add to list
		if (item.getToken() == 'A'){
			aList.add((Autonomous)item);
		}
	}
	
	/**
	 * Print World to console
	 */
	public void display(){
		
		// loop through the array
		for(int i = 0; i < world[0].length; i++){
			for(int j = 0; j < world.length; j++){
				
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
			Autonomous a = aList.get(i);
			int tempx = a.getXCoord();
			int tempy = a.getYCoord();
			a.step();
			int newx = a.getXCoord();
			int newy = a.getYCoord();
			int[] direction = {newx - tempx, newy - tempy};
			
			// invalid new position, move back to old position
			// bumps into Immoveable, move back to old position
			if (newx < 0 || newx > xWorld || newy < 0 || newy > yWorld || 
				world[newx][newy] != null && world[newx][newy].getToken() == 'I'){
				a.setXCoord(tempx);
				a.setYCoord(tempy);
			}
			//  new position is empty, move there
			else if(world[newx][newy] == null){
				world[tempx][tempy] = null;
				add(a, newx, newy);
			}
			// bump into Moveable or another Autonomous
			else{
				boolean success = stepConsequence(newx, newy, direction);
				// Moveable moved, Autonomous can stay at new position
				if(success){
					world[tempx][tempy] = null;
					add(a, newx, newy);
					
					//
					display();
					//
					
				}
				// Moveable failed to move, so Autonomous go back to original position
				else{
					a.setXCoord(tempx);
					a.setYCoord(tempy);
				}
			}
			
		}
	}
	
	/**
	 * Move Moveable and all consequence its step causes
	 * @param x
	 * @param y
	 */
	private boolean stepConsequence(int x, int y, int[] direction){
		Item i = world[x][y];
		
		// double check is Moveable or Autonomous
		if (i.getToken() != 'M' || i.getToken() != 'A'){
			return false;
		}
		
		// step Moveable in the direction of movement
		i.move(direction[0], direction[1]);
		int newx = i.getXCoord();
		int newy = i.getYCoord();
		
		// invalid new position, move back to old position
		// bumps into Immoveable, move back to old position
		if (newx < 0 || newx > xWorld || newy < 0 || newy > yWorld || 
			world[newx][newy] != null && world[newx][newy].getToken() == 'I'){
				i.setXCoord(x);
				i.setYCoord(y);
				return false;
		}
		//  new position is empty, move there
		else if(world[newx][newy] == null){
				world[x][y] = null;
				add(i, newx, newy);
				
				//
				display();
				//
				
				return true;
		}
		// bump into another Moveable or Autonomous
		else{
			// recursive call, same direction
			boolean success = stepConsequence(newx, newy, direction);
			// Item bumped into moved, current Item can stay at new position
			if(success){
				world[x][y] = null;
				add(i, newx, newy);
				
				//
				display();
				//
				
				return true;
			}
			// Item bumped into failed to move, current Item go back to original position
			else{
				i.setXCoord(x);
				i.setYCoord(y);
				return false;
			}
		}
		
	}
	
	public static void main(String[] args){
		World w = new World(5,5);
		w.display();
		Autonomous a = new Autonomous();
		w.add(a, 2, 2);
		Moveable m = new Moveable();
		w.add(m, 2, 3);
		
		for(int i = 0; 1 < 5; i++){
			w.step();
			w.display();
		}
		
	}
	
}
