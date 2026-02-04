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
        return currentOccupancy < 1; // Max 1 train
    }

    @Override
    public void enter(Train t) throws InterruptedException {
        Direction d = t.getPosition().getDirection();
        
        // Trouver l'élément précédent (d'où vient le train)
        Element previousElement = this.getNext(d == Direction.LR ? Direction.RL : Direction.LR);
        
        // Si on vient d'une Station, on réserve TOUTE la ligne pour cette direction
        if (previousElement instanceof Station) {
            this.railway.acquireLine(d);
        }
        
        // Entrer physiquement dans la section
        super.enter(t);
    }

    @Override
    public synchronized void leave(Train t) {
        Direction d = t.getPosition().getDirection();
        Element nextElement = t.getPosition().getNextElement();

        // Libération de la place physique
        super.leave(t);

        // On libère la ligne UNIQUEMENT si le prochain élément est une gare
        // (= on a terminé de traverser toutes les sections)
        if (nextElement instanceof Station) {
            this.railway.releaseLine(d);
        }
    }
}