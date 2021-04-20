
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.sovelluslogiikka.Markovketju;
import javaMusic.sovelluslogiikka.Trie;
import javax.sound.midi.InvalidMidiDataException;
import noteReader.NoteReader;

public class main {

    public static void main(String[] args) throws IOException, InvalidMidiDataException {

//        int[] odds = new int[10];
//        double[] percentages = new double[10];
//        for (int i = 0; i < odds.length; i++) {
//            odds[i] = i;
//        }
//        int oddSum = 0;
//        for (int i = 0; i < odds.length; i++) {
//            oddSum += odds[i];
//        }
//        System.out.println(Arrays.toString(odds));
//        percentages[0] = odds[0];
//        for (int i = 1; i < odds.length; i++) {
//            System.out.println(odds[i-1] +  " " + odds[i]);
//            percentages[i] = (double) (odds[i] + percentages[i-1]) / oddSum;
//
//        }
//        System.out.println(Arrays.toString(odds));
//        System.out.println(Arrays.toString(percentages));
//        double sum = 0;
//        for (Double d : percentages) {
//            sum += d;
//        }
//        System.out.println("sum : " + sum);
        Trie trie = new Trie();

        try {
            NoteReader notereader = new NoteReader("teentown", trie);
            Markovketju m = new Markovketju();
            String[] freqArray = new String[12];

            for (int i = 0; i < 150; i++) {
                trie.printASequence(trie.getRoot(), freqArray, 0);
            }

        } catch (URISyntaxException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
