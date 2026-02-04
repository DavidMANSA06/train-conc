
/**
* @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 */
//

package train;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Test Exercice 4 : Gare Intermédiaire ===\n");

        // Configuration : Gare A -- sections -- Gare Intermédiaire -- sections -- Gare D
        Station gareA = new Station("GareA", 3, true);  // Terminale
        Station gareInter = new Station("GareInter", 2, false); // INTERMÉDIAIRE avec 2 places
        Station gareD = new Station("GareD", 3, true);  // Terminale

        Section ab = new Section("AB");
        Section bc = new Section("BC");
        Section cd = new Section("CD");
        Section de = new Section("DE");

        Railway railway = new Railway(new Element[] { 
            gareA, ab, bc, gareInter, cd, de, gareD 
        });

        System.out.println("Ligne : " + railway);
        System.out.println("\nGare intermédiaire : " + gareInter);
        System.out.println("Capacité réelle utilisable : " + (gareInter.getSize() - 1) + 
                         " (1 place réservée pour éviter deadlock)\n");

        try {
            // Test avec 4 trains (n+2 avec n=2)
            Position posLR1 = new Position(gareA, Direction.LR);
            Position posLR2 = new Position(gareA, Direction.LR);
            Position posRL1 = new Position(gareD, Direction.RL);
            Position posRL2 = new Position(gareD, Direction.RL);

            Train t1 = new Train("LR-1", posLR1);
            Train t2 = new Train("LR-2", posLR2);
            Train t3 = new Train("RL-1", posRL1);
            Train t4 = new Train("RL-2", posRL2);

            System.out.println("Lancement de 4 trains (2 LR, 2 RL)...");
            System.out.println("Sans la réservation de place, il y aurait deadlock!\n");

            new Thread(t1).start();
            Thread.sleep(500);
            new Thread(t2).start();
            Thread.sleep(500);
            new Thread(t3).start();
            Thread.sleep(500);
            new Thread(t4).start();

        } catch (BadPositionForTrainException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}