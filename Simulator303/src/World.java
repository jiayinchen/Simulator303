import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author JiaYin Chen #260690708, Yu Qi Liu #260687081
 * World simulator
 */
public class World {

	private Item[][] world;
	private ArrayList<Autonomous> aList = new ArrayList<Autonomous>();
	private ArrayList<Moveable> mList = new ArrayList<Moveable>();
	private ArrayList<Immoveable> iList = new ArrayList<Immoveable>(); 
	
	/**
	 * Constructor for World
	 */
	public World(int x, int y){
		world = new Item[x][y];
	}
	
	/**
	 * One step for each Autonomous Item in World and its consequences
	 */
	public void step(){
		
		// for every Autonomous Item
		for(int i = 0; i < aList.size(); i++){
			Autonomous a = aList.get(i);
			int [] prevAutoCoor = a.getLocation();
			a.step();
			int [] currAutoCoor = a.getLocation();

	
			// check if new location is valid
			if( currAutoCoor[0] < 0 || currAutoCoor[0] > world[0].length || 
				currAutoCoor[1] < 0 || currAutoCoor[1] > world.length){
				System.out.println("Autonomous, can’t move in this direction");
				display();
				return;
			}
	
			//  new location is empty cell
			if(world[currAutoCoor[0]][currAutoCoor[1]] == null){
				world[prevAutoCoor[0]][prevAutoCoor[1]] =  null;
				world[currAutoCoor[0]][currAutoCoor[1]] = a;
				display();
				return;
			}
			// new location hits Immoveable Item
			else if(world[currAutoCoor[0]][currAutoCoor[1]].getToken() == 'I'){
				a.setLocation(prevAutoCoor);
				System.out.println("Autonomous, can’t move in this direction");
				display();
				return;
			}
			// new location hits Moveable Item
			else if(world[currAutoCoor[0]][currAutoCoor[1]].getToken() == 'M'){
					Item mItem = world[currAutoCoor[0]][currAutoCoor[1]];
	
					// get direction of movement
					int x= currAutoCoor[0]-prevAutoCoor[0];
					int y= currAutoCoor[1]-prevAutoCoor[1];
						
					// can't move Moveable Item
					if( currAutoCoor[0] + x > world[0].length || currAutoCoor[1] + y > world.length){
						a.setLocation(prevAutoCoor);
						System.out.println("Autonomous, can’t move in this direction");
						display();
						return;
					}
					// move Moveable Item
					else{
						int[] temp = new int[2];
						temp[0] = x + currAutoCoor[0];
						temp[1] = y + currAutoCoor[1];
						mItem.setLocation(temp);
						
						world[prevAutoCoor[0]][prevAutoCoor[1]] =  null;
						world[currAutoCoor[0]][currAutoCoor[1]] = a;
						world[temp[0]][temp[1]] =  null;
						world[temp[0]][temp[1]] = mItem;
						
						display();
						return;
					}
	
			}
			// new location hits another Immoveable Item
			else{
				
			}
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
	 * Add Item to World in a random cell
	 * @param item Item to add
	 */
	public void add(Item item){
		
		// loop until Item is added
		while(true){
			
			// generate random coordinate
			int x = (int)(Math.random() * world[0].length);
			int y = (int)(Math.random() * world.length);
			
			// add item to empty cell
			if(world[x][y] == null){
				
				// add item to respective list
				if(item.getToken() == 'A'){
					Autonomous a = (Autonomous) item;
					aList.add(a);
					world[x][y] = a;
				}
				else if(item.getToken() == 'I'){
					Immoveable a = (Immoveable) item;
					iList.add(a);
					world[x][y] = a;
				}
				else{
					Moveable a = (Moveable)item;
					mList.add(a);
					world[x][y] = a;
				}
				
				return;
			}
			
		}
		
	}
	
	
	/**
	 * Main method. Runs 100 simulations.
	 * @param args
	 */
	public static void main(String[] args){
		
		World world = new World(5, 5);
		world.add(new Autonomous(0,0));
		world.add(new Moveable(0,1));
		world.add(new Immoveable(0,2));
		
		for(int i = 0; i < 5 ; i++){
			world.step();
		}
		
		
	}
	
}
