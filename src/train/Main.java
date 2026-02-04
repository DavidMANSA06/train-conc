
/**
* @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 */
//

package train;

public class Main {
    public static void main(String[] args) {
        // 1. Configuration : Une ligne avec plusieurs sections entre deux gares
        Station gareA = new Station("GareA", 2);
        Station gareD = new Station("GareD", 2);
        Section ab = new Section("AB");
        Section bc = new Section("BC");
        Section cd = new Section("CD");
        
        Railway railway = new Railway(new Element[] { gareA, ab, bc, cd, gareD });
        
        System.out.println("Test de l'Exercice 3 - Prévention des interblocages");
        System.out.println("Ligne : " + railway);

        // 2. Positions de départ opposées
        Position posLR = new Position(gareA, Direction.LR);
        Position posRL = new Position(gareD, Direction.RL);

        try {
            // 3. Création des trains
            Train train1 = new Train("LR-1", posLR);
            Train train2 = new Train("RL-2", posRL);

            // 4. Lancement simultané
            // Le moniteur Railway doit empêcher RL-2 d'entrer sur les sections 
            // tant que LR-1 n'est pas arrivé en GareD.
            System.out.println("Lancement des trains en sens inverses...");
            new Thread(train1).start();
            new Thread(train2).start();

        } catch (BadPositionForTrainException e) {
            e.printStackTrace();
        }
    }
}
