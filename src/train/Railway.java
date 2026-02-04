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
            while (nbRL > 0) {
                System.out.println("  [WAITING] Train LR waiting because " + nbRL + " train(s) RL on line");
                wait();
            }
            nbLR++;
            System.out.println("  [ACQUIRED] Line reserved for LR direction (nbLR=" + nbLR + ")");
        } else {
            while (nbLR > 0) {
                System.out.println("  [WAITING] Train RL waiting because " + nbLR + " train(s) LR on line");
                wait();
            }
            nbRL++;
            System.out.println("  [ACQUIRED] Line reserved for RL direction (nbRL=" + nbRL + ")");
        }
    }

    public synchronized void releaseLine(Direction dir) {
        if (dir == Direction.LR) {
            if (nbLR > 0) {
                nbLR--;
                System.out.println("  [RELEASED] Line released by LR train (nbLR=" + nbLR + ")");
            } else {
                System.err.println("Warning: nbLR already at 0!");
            }
        } else {
            if (nbRL > 0) {
                nbRL--;
                System.out.println("  [RELEASED] Line released by RL train (nbRL=" + nbRL + ")");
            } else {
                System.err.println("Warning: nbRL already at 0!");
            }
        }
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