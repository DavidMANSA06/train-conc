package train;

/**
 * Représentation de la position d'un train dans le circuit. Une position
 * est caractérisée par deux valeurs :
 * <ol>
 *   <li>
 *     L'élément où se positionne le train : une gare (classe  {@link Station})
 *     ou une section de voie ferrée (classe {@link Section}).
 *   </li>
 *   <li>
 *     La direction qu'il prend (enumération {@link Direction}) : de gauche à
 *     droite ou de droite à gauche.
 *   </li>
 * </ol>
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr> Modifié par Mayte
 *         Segarra 
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 *         
 * @version 0.3
 */
public class Position implements Cloneable {
	private Direction direction;
	private Element pos;
	//private Element[] elts; 

	public Position(Element elt, Direction d) {
		if (elt == null || d == null)
			throw new NullPointerException();

		this.pos = elt;
		this.direction = d;
	}

	@Override
	public Position clone() {
		try {
			return (Position) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Element getPos() {
		return pos;
	}
	
	public Direction getDirection() {
        return direction;
    }

	/* la méthode moveToNext() déplace la position vers
	 l'élément suivant dans la direction actuelle */
	public void moveToNext() {
        Element next = pos.getNext(direction);
        if (next != null) {
            this.pos = next;
        }
    }
	
	/* la méthode reverse() inverse la direction actuelle */
	public void reverse() {
        if (direction == Direction.LR) {
            direction = Direction.RL;
        } else {
            direction = Direction.LR;
        }
    }
	
	/* la méthode getNextElement() retourne l'élément suivant dans la direction actuelle,
	 ou null s'il n'y en a pas */
	public Element getNextElement() {
        return pos.getNext(direction);
    }
	
	
	

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(this.pos.toString());
		result.append(" going ");
		result.append(this.direction);
		return result.toString();
	}
}
