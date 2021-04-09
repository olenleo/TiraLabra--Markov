
import java.io.IOException;
import java.net.URISyntaxException;
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
            NoteReader notereader = new NoteReader("fbts", trie);
        } catch (URISyntaxException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Note[] list = {new Note(65,235440,235680), new Note(65,235920,236160), new Note(65,236160,236640), new Note(65,236640,237120), new Note(65,237120,237600)};
        
        System.out.println(trie.search(list));
    }

}
