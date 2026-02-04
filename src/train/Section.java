package train;

/**
 * Représentation d'une section de voie ferrée. C'est une sous-classe de la
 * classe {@link Element}.
 *
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */

public class Section extends Element {
    public Section(String name) {
        super(name);
    }

    @Override
    protected boolean canEnter() {
        return nbTrains < 1; // Max 1 train
    }

    @Override
    public void enter(Train t) throws InterruptedException {
        Direction d = t.getPosition().getDirection();
        Element previousElement = getPreviousElement(d);

        // Réserver la ligne si on sort d'une gare TERMINALE
        if (previousElement instanceof Station && ((Station) previousElement).isTerminal()) {
            this.railway.acquireLine(d);
        }

        super.enter(t);
    }

    @Override
    public synchronized void leave(Train t) {
        Direction d = t.getPosition().getDirection();
        Element nextElement = t.getPosition().getNextElement();

        super.leave(t);

        // Libérer la ligne si on entre dans une gare TERMINALE
        if (nextElement instanceof Station && ((Station) nextElement).isTerminal()) {
            this.railway.releaseLine(d);
        }
    }

    // Helper method pour trouver l'élément précédent
    private Element getPreviousElement(Direction d) {
        Direction opposite = (d == Direction.LR) ? Direction.RL : Direction.LR;
        return this.getNext(opposite);
    }
}