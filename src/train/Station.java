package train;

/**
 * Représentation d'une gare. C'est une sous-classe de la classe {@link Element}.
 * Une gare est caractérisée par un nom et un nombre de quais (donc de trains
 * qu'elle est susceptible d'accueillir à un instant donné).
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Station extends Element {
    private final int size;
    private final boolean isTerminal; // NEW: Distinguer gare terminale vs intermédiaire

    // Constructeur pour gares terminales (comportement par défaut)
    public Station(String name, int size) {
        this(name, size, true);
    }

    // NEW: Constructeur avec type de gare
    public Station(String name, int size, boolean isTerminal) {
        super(name);
        if(name == null || size <= 0)
            throw new NullPointerException();
        this.size = size;
        this.isTerminal = isTerminal;
    }

    @Override
    protected boolean canEnter() {
        if (isTerminal) {
            // Gare terminale : peut accueillir tous les trains jusqu'à 'size'
            return nbTrains < size;
        } else {
            // Gare intermédiaire : RÉSERVE 1 PLACE pour éviter le deadlock
            // Cette place permet aux trains en sens inverse de passer
            return nbTrains < (size - 1);
        }
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        String type = isTerminal ? "Terminal" : "Intermediate";
        return super.toString() + "[" + type + ",size=" + size + "]";
    }
}

