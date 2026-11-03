// Punto 1: Calcula el saldo final entre votos a favor (upvotes) y en contra (downvotes).
public class Punto1_Votos {

    public static int getVoteCount(int upvotes, int downvotes) {
        return upvotes - downvotes;
    }

    public static void main(String[] args) {
        System.out.println("Prueba 1 (13, 0): " + getVoteCount(13, 0));       // 13
        System.out.println("Prueba 2 (2, 33): " + getVoteCount(2, 33));       // -31
        System.out.println("Prueba 3 (132, 132): " + getVoteCount(132, 132)); // 0
    }
}
