
import java.io.IOException;
import javaMusic.sovelluslogiikka.Trie;
import javax.sound.midi.InvalidMidiDataException;

public class main {

    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        Trie trie = new Trie();
        
              
        
        trie.add("Rapu");
        trie.add("Rapina");
        trie.add("Rapistuu");
        trie.add("Rapistuva");
        
        System.out.println(trie.search("Rapu"));
        System.out.println(trie.search("Rapistuu"));
        System.out.println(trie.search("Rapiseva"));

        
    }

}
