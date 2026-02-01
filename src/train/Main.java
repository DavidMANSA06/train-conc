
/**
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 */


package train;

public class Main {
    public static void main(String[] args) {

        // Création des éléments
        Station A = new Station("GareA", 2);   // 2 quais
        Station D = new Station("GareD", 2);   // 2 quais

        Section AB = new Section("AB");
        Section BC = new Section("BC");
        Section CD = new Section("CD");

        // Ligne de chemin de fer
        Railway r = new Railway(new Element[] { A, AB, BC, CD, D });

        System.out.println("Railway:");
        System.out.println("  " + r);
        System.out.println();

        // Position initiale (tous les trains partent de GareA)
        Position p1 = new Position(A, Direction.LR);
        Position p2 = new Position(A, Direction.LR);
        Position p3 = new Position(A, Direction.LR);

        try {
            Train t1 = new Train("T1", p1);
            Train t2 = new Train("T2", p2);
            Train t3 = new Train("T3", p3);

            Thread th1 = new Thread(t1);
            Thread th2 = new Thread(t2);
            Thread th3 = new Thread(t3);

            System.out.println("Starting trains...");
            th1.start();

            // Démarrage progressif pour bien observer la synchronisation
            Thread.sleep(3000);
            th2.start();

            Thread.sleep(3000);
            th3.start();

        } catch (BadPositionForTrainException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
