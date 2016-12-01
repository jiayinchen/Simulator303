
public abstract class Item {

	private String name;
	private char symbol;
	private int row;
	private int col;
	
	/**
	 * Constructor for Item
	 * @param theName name of Item
	 * @param theSymbol symbol representing Item
	 * @param theRow position row x
	 * @param theCol position col y
	 */
	public Item(String theName, char theSymbol, int theRow, int theCol){
		name = theName;
		symbol = theSymbol;
		row = theRow;
		col = theCol;
	}
	
	/**
	 * Move Item of one cell in direction provided
	 * @param rowShift row position shift
	 * @param colShift column position shift
	 */
	public boolean move(int rowShift, int colShift){
		
		// precondition
		if(  rowShift < -1 || rowShift > 1 || colShift < -1 || colShift > 1){
			throw new IllegalArgumentException("Item can only move one cell at a time.");
		}
		// cannot move diagonally
		else if(rowShift != 0 && colShift != 0){
			throw new IllegalArgumentException("Item cannot move diagonally.");
		}
		
		int tempR = row + rowShift;
		int tempC = col + colShift; 
		
		// new position is invalid, don't move
		if(tempR < 0 || tempC < 0 ){
			return false;
		}
		else{
			// move item of one cell
			row += rowShift;
			col += colShift;
			return true;
		}	
	}
	
	/**
	 * Symbol getter
	 * @return the symbol representing Item
	 */
	public char getToken(){
		return symbol;
	}
	
	/**
	 * Row number getter
	 * @return x row position of Item
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * Col number getter
	 * @return y column position of Item
	 */
	public int getCol(){
		return col;
	}
	
	/**
	 * row number setter
	 * @param x new row position of Item
	 */
	public void setRow(int x){
		// precondition
		if ( x < 0){
			throw new IllegalArgumentException("Invalid row number.");
		}
		
		row = x;
	}
	
	/**
	 * col number setter
	 * @param y new column position of Item
	 */
	public void setCol(int y){
		// precondition
		if ( y < 0){
			throw new IllegalArgumentException("Invalid column number.");
		}
		
		col = y;
	}
	
}
