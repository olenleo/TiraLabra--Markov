package javaMusic.kayttoliittyma;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.Main;
import javaMusic.notePrinter.SonicPiFormat;
import javaMusic.sovelluslogiikka.MarkovGenerator;
import javaMusic.sovelluslogiikka.Trie;
import javaMusic.sovelluslogiikka.TrieNode;
import javaMusic.noteReader.NoteReader;

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
    private String[][] data;
    private SonicPiFormat formatter;

    public TextUI() {
        this.filename = "";

    }

    public void start() {
        scanner = new Scanner(System.in);
        readUserInstructions();
        System.out.println(filename);
        trie = new Trie(len);
        data = new String[amount][len];
        root = trie.getRoot();

        try {
            NoteReader notereader = new NoteReader(filename, trie, len);
            notereader.read();
            markovGenerator = new MarkovGenerator(notereader.getDivision());
            String[] freqArray = new String[len];
            System.out.println(data.length);
            System.out.println(data[0].length);
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < amount; j++) {
                    String[] s = markovGenerator.generateSequence(root, freqArray, 0);

                    data[j][i] = s[i];
                }

            }

            formatter = new SonicPiFormat(data);

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
