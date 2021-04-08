
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.sovelluslogiikka.Trie;
import javaMusic.sovelluslogiikka.Markovketju;
import javaMusic.sovelluslogiikka.Note;
import javax.sound.midi.InvalidMidiDataException;
import noteReader.NoteReader;

public class main {

    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        Trie trie = new Trie();
        Markovketju m = new Markovketju();

        try {
            NoteReader notereader = new NoteReader("Tira_Major_120BPM", trie);
        } catch (URISyntaxException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Note[] list = {new Note(26,480,960), new Note(28,960,1440), new Note(29,1440,1920), new Note(31,1920,2400), new Note(33,2400,2880)};
//        trie.add(list);
        System.out.println(trie.search(list));
    }

}
