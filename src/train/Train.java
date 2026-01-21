package train;

/**
 * Représentation d'un train. Un train est caractérisé par deux valeurs :
 * <ol>
 *   <li>
 *     Son nom pour l'affichage.
 *   </li>
 *   <li>
 *     La position qu'il occupe dans le circuit (un élément avec une direction) : classe {@link Position}.
 *   </li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Mayte segarra <mt.segarra@imt-atlantique.fr>
 * Test if the first element of a train is a station
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 * @version 0.3
 */
public class Train implements Runnable {
	private final String name;
	private final Position pos;

	public Train(String name, Position p) throws BadPositionForTrainException {
		if (name == null || p == null)
			throw new NullPointerException();

		// A train should be first be in a station
		if (!(p.getPos() instanceof Station))
			throw new BadPositionForTrainException(name);

		this.name = name;
		this.pos = p.clone();
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Train[");
		result.append(this.name);
		result.append("]");
		result.append(" is on ");
		result.append(this.pos);
		return result.toString();
	}
	
	public void move() {
        Element current = pos.getPos();
        Element next = pos.getNextElement();

        if (next == null) {
            pos.reverse();
            System.out.println(this.name + " reversed direction at " + current);
        } else {
            current.leave(this);
            
            // Move to next element
            pos.moveToNext();
            
            // Enter next element
            next.enter(this);
            
            System.out.println(this.name + " moved to " + next);
        }
    }

	@Override
	public void run() {
		System.out.println("Train " + this.name + " starting at " + pos.getPos());
        
        while (true) {
            try {
                // Move the train
                move();
                
                // Sleep for a bit to simulate travel time
                Thread.sleep(1000); // 1 second between moves
                
            } catch (InterruptedException e) {
                System.out.println("Train " + this.name + " interrupted");
                Thread.currentThread().interrupt();
            }
        }
	}
}
