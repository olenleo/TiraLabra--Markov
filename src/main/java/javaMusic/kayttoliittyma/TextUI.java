package javaMusic.kayttoliittyma;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.Main;
import javaMusic.sovelluslogiikka.MarkovGenerator;
import javaMusic.sovelluslogiikka.Trie;
import javaMusic.sovelluslogiikka.TrieNode;
import noteReader.NoteReader;

/**
 * Yksinkertainen tekstikäyttöliittymä joka kysyy tiedostonimeä,
 *
 * @author Leo Niemi
 */
public class TextUI {

    private Scanner scanner;
    private Trie trie;
    private TrieNode root;
    private MarkovGenerator markovGenerator;
    private String filename;
    private int amount, len;

    public TextUI() {
        this.filename = "";

    }

    public void start() {
        scanner = new Scanner(System.in);
        readUserInstructions();
        System.out.println(filename);
        trie = new Trie(len);
        root = trie.getRoot();

        try {
            NoteReader notereader = new NoteReader(filename, trie, len);

            notereader.read();
            int div = notereader.getDivision();
            markovGenerator = new MarkovGenerator(notereader.getDivision());
            String[] freqArray = new String[len];
            for (int i = 0; i < amount; i++) {
                markovGenerator.generateSequence(root, freqArray, 0);
            }
//            trie.printTrie(trie.getRoot(), new String[len], 0);

        } catch (URISyntaxException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readUserInstructions() {
        System.out.println("The program reads a .csv file in the resources folder and prints generated melodies");
        System.out.println("Enter .csv filename");
        this.filename = scanner.nextLine();
        System.out.println("Enter length of note sequences");
        this.len = scanner.nextInt();
        System.out.println("Enter amount of note sequences");
        this.amount = scanner.nextInt();
    }

    public int getLen() {
        return len;
    }
}
