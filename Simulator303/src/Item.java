/**
 * 
 * @author JiaYin Chen #260690708, Yu Qi Liu #260687081
 * Item of a World simulator
 */
public abstract class Item {
	
	private String name;
	private char symbol;
	private int[] coordinates;
	
	/**
	 * Constructor for Item
	 * @param theName name of Item
	 * @param theSymbol symbol representing Item
	 */
	public Item(String theName, char theSymbol, int x, int y){
		coordinates = new int[2];
		coordinates[0] = x;
		coordinates[1] = y;
		name = theName;
		symbol = theSymbol;
	}
	
	
	/**
	 * Item location getter
	 * @return location of Item
	 */
	public int[] getLocation(){
		return coordinates;
	}

	
	/**
	 * Item location setter
	 * @param newCoord new location of Item
	 */
	public void setLocation(int[] newCoord){
		coordinates = newCoord;
	}
	
	/**
	 * Item symbol getter
	 * @return symbol representing Item
	 */
	public char getToken(){
		return symbol;
	}
	
	
}
