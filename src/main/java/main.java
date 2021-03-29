
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.sovelluslogiikka.Trie;
import javaMusic.sovelluslogiikka.Markovketju;
import javax.sound.midi.InvalidMidiDataException;
import noteReader.NoteReader;

public class main {

    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        try {
            //        Trie trie = new Trie();
            
            
//
//        trie.add("Rapu");
//        trie.add("Rapina");
//        trie.add("Rapistuu");
//        trie.add("Rapistuva");
//        
//        System.out.println(trie.search("Rapu"));
//        System.out.println(trie.search("Rapistuu"));
//        System.out.println(trie.search("Rapiseva"));
//
//        
//        Markovketju m = new Markovketju();
//        ArrayList<String> chordprogression = new ArrayList<>();
//        String curr = "C";
//        for (int i = 0; i < 30; i++) {
//            curr = m.getChord(curr);
//            chordprogression.add(curr);
//            
//        }
//        System.out.println(chordprogression);
//        System.out.println(m.getPiChords());
        NoteReader notereader = new NoteReader();
        } catch (URISyntaxException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
