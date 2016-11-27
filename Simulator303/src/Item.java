/**
 * 
 * @author JiaYin Chen #260690708, Yu Qi Liu #260687081
 * Item of a World simulator
 */
public abstract class Item {
	
	private String name;
	private char symbol;
	
	/**
	 * Constructor for Item
	 * @param theName name of Item
	 * @param theSymbol symbol representing Item
	 */
	public Item(String theName, char theSymbol){
		name = theName;
		symbol = theSymbol;
	}
	
	/**
	 * Item symbol getter
	 * @return symbol representing Item
	 */
	public char getToken(){
		return symbol;
	}

	
}
