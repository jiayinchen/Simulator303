
public class Immoveable extends Item{

	/**
	 * Constructor for Immoveable
	 */
	public Immoveable(){
		super("Immmoveable", 'I', 0, 0);
	}
	
	@Override
	public void move(int x, int y){
		throw new IllegalArgumentException("Immoveable cannot move.");
	}
	
}
