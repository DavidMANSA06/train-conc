package train;


/**
 * Représentation d'un circuit constitué d'éléments de voie ferrée : gare ou
 * section de voie
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Railway {
	public final Element[] elements;
	private Position pos;
	
	private int nbLR = 0;
	private int nbRL = 0;
	

	public Railway(Element[] elements) {
		if(elements == null)
			throw new NullPointerException();
		
		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);
	}
	
	public synchronized void acquireLine(Direction dir) throws InterruptedException {
	    if (dir == Direction.LR) {
	        while (nbRL > 0) wait();
	        nbLR++;
	    } else {
	        while (nbLR > 0) wait();
	        nbRL++;
	    }
	}

	public synchronized void releaseLine(Direction dir) {
	    if (dir == Direction.LR) nbLR--;
	    else nbRL--;
	    notifyAll();
	}
	
	
    public Element getElement(int index) {
        if (index >= 0 && index < elements.length) {
            return elements[index];
        }
        return null;
    }

    public int indexOf(Element e) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return elements.length;
    }
	

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Element e : this.elements) {
			if (first)
				first = false;
			else
				result.append("--");
			result.append(e);
		}
		return result.toString();
	}
	
}
