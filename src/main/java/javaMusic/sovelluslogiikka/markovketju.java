package javaMusic.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka sisältää toiminnallisuuden C-duurissa liikkuvan sointukierron
 * generoimiseen.
 *
 * @author Leo Niemi
 */


public class Markovketju {

    private double[][] todennakoisyysmatriisi;
    private String chords[] = {"G", "F", "Am", "Dm", "G/B", "C"};
    private Random r;
    private ArrayList<String> piChords = new ArrayList<>();

    /**
     * Jokainen rivi ilmaisee lähtösoinnun ja jokainen sarake
     * ilmaisee todennäköisyyttä siirtyä kyseiseen sointuun.
    */
    public Markovketju() {
        r = new Random();

        todennakoisyysmatriisi = new double[6][6];
        // G
        todennakoisyysmatriisi[0][0] = 0.12; // G
        todennakoisyysmatriisi[0][1] = todennakoisyysmatriisi[0][0] + 0.27; // F
        todennakoisyysmatriisi[0][2] = todennakoisyysmatriisi[0][1] + 0.32; // Am
        todennakoisyysmatriisi[0][3] = todennakoisyysmatriisi[0][2] + 0.12; // Dm
        todennakoisyysmatriisi[0][4] = todennakoisyysmatriisi[0][3] + 0.07; // G/B
        todennakoisyysmatriisi[0][5] = todennakoisyysmatriisi[0][4] + 0.10; // C
        // F
        todennakoisyysmatriisi[1][0] = 0.29;                                // G
        todennakoisyysmatriisi[1][1] = todennakoisyysmatriisi[1][0] + 0.27; // F
        todennakoisyysmatriisi[1][2] = todennakoisyysmatriisi[1][1] + 0.1; // Am
        todennakoisyysmatriisi[1][3] = todennakoisyysmatriisi[1][2] + 0.05; // Dm
        todennakoisyysmatriisi[1][4] = todennakoisyysmatriisi[1][3] + 0; // G/B
        todennakoisyysmatriisi[1][5] = todennakoisyysmatriisi[1][4] + 0.29; // C       
       
        // Am
        todennakoisyysmatriisi[2][0] = 0.26;                                // G
        todennakoisyysmatriisi[2][1] = todennakoisyysmatriisi[2][0] + 0.24; // F
        todennakoisyysmatriisi[2][2] = todennakoisyysmatriisi[2][1] + 0.31; // Am
        todennakoisyysmatriisi[2][3] = todennakoisyysmatriisi[2][2] + 0.06; // Dm
        todennakoisyysmatriisi[2][4] = todennakoisyysmatriisi[2][3] + 0.02; // G/B
        todennakoisyysmatriisi[2][5] = todennakoisyysmatriisi[2][4] + 0.11; // C
 
        // Dm
        todennakoisyysmatriisi[3][0] = 0.16;                                // G
        todennakoisyysmatriisi[3][1] = todennakoisyysmatriisi[3][0] + 0.16; // F
        todennakoisyysmatriisi[3][2] = todennakoisyysmatriisi[3][1] + 0.18; // Am
        todennakoisyysmatriisi[3][3] = todennakoisyysmatriisi[3][2] + 0.363; // Dm
        todennakoisyysmatriisi[3][4] = todennakoisyysmatriisi[3][3] + 0.007; // G/B
        todennakoisyysmatriisi[3][5] = todennakoisyysmatriisi[3][4] + 0.13; // C

        // G/B
        todennakoisyysmatriisi[4][0] = 0.07;                                // G
        todennakoisyysmatriisi[4][1] = todennakoisyysmatriisi[4][0] + 0.07; // F
        todennakoisyysmatriisi[4][2] = todennakoisyysmatriisi[4][1] + 0.27; // Am
        todennakoisyysmatriisi[4][3] = todennakoisyysmatriisi[4][2] + 0.04; // Dm
        todennakoisyysmatriisi[4][4] = todennakoisyysmatriisi[4][3] + 0.27; // G/B
        todennakoisyysmatriisi[4][5] = todennakoisyysmatriisi[4][4] + 0.28; // C

        // C
        todennakoisyysmatriisi[5][0] = 0.25;                                // G
        todennakoisyysmatriisi[5][1] = todennakoisyysmatriisi[5][0] + 0.19; // F
        todennakoisyysmatriisi[5][2] = todennakoisyysmatriisi[5][1] + 0.10; // Am
        todennakoisyysmatriisi[5][3] = todennakoisyysmatriisi[5][2] + 0.06; // Dm
        todennakoisyysmatriisi[5][4] = todennakoisyysmatriisi[5][3] + 0.05; // G/B
        todennakoisyysmatriisi[5][5] = todennakoisyysmatriisi[5][4] + 0.35; // G/B
       
//        for (int i = 0; i< todennakoisyysmatriisi.length; i++) {
//            for (int j = 0; j< todennakoisyysmatriisi.length; j++) {
//                System.out.print("[" +todennakoisyysmatriisi[i][j]+"]");
//            }
//            System.out.println("");
//        }
    }

    /**
     * Metodi arpoo uuden soinnun edellisen perusteella.
     *
     * @param curr Nykyinen sointu merkkijonomuodossa - ["G", "F", "Am, "Dm",
     * "G/B", "C"]
     * @return jokin merkkijonoista ["G", "F", "Am, "Dm", "G/B", "C"]
     */
    public String getChord(String curr) {
        switch (curr) {
            case "G":
                return getChordFromIndex(0);                
            case "F":
                return getChordFromIndex(1);
            case "Am":
                return getChordFromIndex(2);
            case "Dm":
                return getChordFromIndex(3);
            case "G/B":
                return getChordFromIndex(4);
            case "C":
                return getChordFromIndex(5);
            default:
                break;
        }
        return "C"; // palataan juureen!
    }
    
    /**
     * Metodi arpoo uuden soinnun todennäköisyysmatriisista.
     * @param row TODO: viittaus lähtösointuun merkkijonona, eikä taulukon indeksinä.
     * @return palauttaa uuden soinnun merkkijonoista ["G", "F", "Am, "Dm", "G/B", "C"].
     */
    private String getChordFromIndex(int row) {
        double k = r.nextDouble();
        for (int i = 0; i < todennakoisyysmatriisi.length; i++) {
            // System.out.println("k:[" + k + "], matrix[" + row +"][" + i + "] = " + todennakoisyysmatriisi[row][i]  + " => " + chords[i] );
            if (todennakoisyysmatriisi[row][i] >= k) {
                System.out.println(chords[i]);
                piChords.add("chord(:" + chords[i] + ", :major)");
            return chords[i];
            }
        }
        return "C";
    }
    /**
     * Palauttaa generoidyt soinnut Sonic PI:n hyväksymässä formaatissa.
     * @return formatoitu ArrayList
     */
    public ArrayList<String> getPiChords() {
        return this.piChords;
    }
}
