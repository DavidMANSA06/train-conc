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
public class Train {
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
		return "Train[" + name + "] is on " + pos;
	}
	
	/* la méthode move() doit faire avancer le train d'un élément dans la direction indiquée par sa position actuelle. 
	 * Si le train arrive à une gare terminale, il doit inverser la direction et faire le déplacement dans la nouvelle direction.
	 * Après chaque déplacement ou changement de direction, la méthode doit afficher l'état actuel du train.
	*/ 
	public void move() {
        Element next = pos.getNextElement();

        if (next == null) {
            pos.reverse();
            System.out.println("Train " + this.name + " reversed direction");
        } else {
            
            // Move to next element
            pos.moveToNext();
            
            System.out.println(this);
        }
    }
}
