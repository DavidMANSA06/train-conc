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
	

	public Railway(Element[] elements) {
		if(elements == null)
			throw new NullPointerException();
		
		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);
	}
	
    /* la méthode getElement() retourne l'élément à l'index donné dans le circuit,
     ou null si l'index est invalide */
    public Element getElement(int index) {
        if (index >= 0 && index < elements.length) {
            return elements[index];
        }
        return null;
    }

    /* la méthode indexOf() retourne l'index de l'élément donné dans le circuit,
     ou -1 si l'élément n'est pas présent */
    public int indexOf(Element e) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /* la méthode size() retourne le nombre d'éléments dans le circuit */
    public int size() {
        return elements.length;
    }
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            if (i > 0) sb.append("--");
            sb.append(elements[i]);
        }
        return sb.toString();
	}
	
}
