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
    public synchronized void enter(Train t) throws InterruptedException {
        Direction d = t.getPosition().getDirection();
        Element currentPos = t.getPosition().getPos();

        if (currentPos instanceof Station) {
            this.railway.acquireLine(d);
        }
        
        super.enter(t);
    }

    @Override
    public synchronized void leave(Train t) {
        Direction d = t.getPosition().getDirection();
        Element nextElement = t.getPosition().getNextElement();

        super.leave(t);

       
        if (nextElement instanceof Station) {
            this.railway.releaseLine(d);
        }
    }
}
