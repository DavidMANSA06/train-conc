//
///**
// * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
// */
//
//
//package train;
//
//public class Main {
//    public static void main(String[] args) {
//
//        // Création des éléments
//        Station A = new Station("GareA", 2);   // 2 quais
//        Station D = new Station("GareD", 2);   // 2 quais
//
//        Section AB = new Section("AB");
//        Section BC = new Section("BC");
//        Section CD = new Section("CD");
//
//        // Ligne de chemin de fer
//        Railway r = new Railway(new Element[] { A, AB, BC, CD, D });
//
//        System.out.println("Railway:");
//        System.out.println("  " + r);
//        System.out.println();
//
//        // Position initiale (tous les trains partent de GareA)
//        Position p1 = new Position(A, Direction.LR);
//        Position p2 = new Position(A, Direction.LR);
//        Position p3 = new Position(A, Direction.LR);
//
//        try {
//            Train t1 = new Train("T1", p1);
//            Train t2 = new Train("T2", p2);
//            Train t3 = new Train("T3", p3);
//
//            Thread th1 = new Thread(t1);
//            Thread th2 = new Thread(t2);
//            Thread th3 = new Thread(t3);
//
//            System.out.println("Starting trains...");
//            th1.start();
//
//            // Démarrage progressif pour bien observer la synchronisation
//            Thread.sleep(3000);
//            th2.start();
//
//            Thread.sleep(3000);
//            th3.start();
//
//        } catch (BadPositionForTrainException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}





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
