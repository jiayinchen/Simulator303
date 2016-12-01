
public class Immoveable extends Item{

	
	public Immoveable(int row, int col){
		super("Immmoveable", 'I', row, col);
	}
	
	/**
	 * Immoveable Item cannot move
	 */
	@Override
	public boolean move(int row, int col){
		throw new IllegalArgumentException("Immoveable cannot move.");
	}
	
}
