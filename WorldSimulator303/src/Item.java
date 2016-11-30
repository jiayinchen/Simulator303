
public abstract class Item {

	private String name;
	private char symbol;
	private int xCoord;
	private int yCoord;
	
	/**
	 * Constructor for Item
	 * @param theName name of Item
	 * @param theSymbol symbol representing Item
	 * @param theX starting x coordinate for Item
	 * @param theY starting y coordinate for Item
	 */
	public Item(String theName, char theSymbol, int theX, int theY){
		name = theName;
		symbol = theSymbol;
		xCoord = theX;
		yCoord = theY;
	}
	
	/**
	 * Move Item of one cell
	 * @param x change in x direction
	 * @param y change in y direction
	 */
	public void move(int x, int y){
		
		// precondition
		if( x < -1 || x > 1 || y < -1 || y > 1 || x != 0 && y != 0){
			throw new IllegalArgumentException("Item can only move one cell at a time.");
		}
		
		// move item of one cell
		xCoord += x;
		yCoord += y;
		
	}
	
	/**
	 * Symbol getter
	 * @return the symbol representing Item
	 */
	public char getToken(){
		return symbol;
	}
	
	/**
	 * x coordinate getter
	 * @return x coordinate of Item
	 */
	public int getXCoord(){
		return xCoord;
	}
	
	/**
	 * y coordinate getter
	 * @return y coordinate of Item
	 */
	public int getYCoord(){
		return yCoord;
	}
	
	/**
	 * x coordinate setter
	 * @param x new x coordinate of Item
	 */
	public void setXCoord(int x){
		// precondition
		if ( x < 0){
			throw new IllegalArgumentException("Invalid x coordinate.");
		}
		
		xCoord = x;
	}
	
	/**
	 * y coordinate setter
	 * @param y new y coordinate of Item
	 */
	public void setYCoord(int y){
		// precondition
		if ( y < 0){
			throw new IllegalArgumentException("Invalid y coordinate.");
		}
		
		yCoord = y;
	}
	
}
