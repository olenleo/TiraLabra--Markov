
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.sovelluslogiikka.Markovketju;
import javaMusic.sovelluslogiikka.Trie;
import javax.sound.midi.InvalidMidiDataException;
import noteReader.NoteReader;

public class main {

    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        Trie trie = new Trie();
        Markovketju m = new Markovketju();

        try {
            NoteReader notereader = new NoteReader("fbts", trie);

            int[] freqArray = new int[5];
            System.out.println("Tulostetaan trie: ");
            trie.printTrie(trie.getRoot(), freqArray, 0);
        } catch (URISyntaxException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
