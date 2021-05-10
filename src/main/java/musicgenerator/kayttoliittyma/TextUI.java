package musicgenerator.kayttoliittyma;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import musicgenerator.Main;
import musicgenerator.note.printer.SonicPiFormat;
import musicgenerator.sovelluslogiikka.MarkovGenerator;
import musicgenerator.sovelluslogiikka.Trie;
import musicgenerator.sovelluslogiikka.TrieNode;
import musicgenerator.note.reader.NoteReader;

/**
 * Yksinkertainen tekstikäyttöliittymä joka kysyy tiedostonimeä, haluttua
 * midi-raitaa, nuottisarjan pituutta ja nuottisarjojen lukumäärää.
 *
 * @author Leo Niemi
 */
public class TextUI {

    private Scanner scanner;
    private Trie trie;
    private TrieNode root;
    private MarkovGenerator markovGenerator;
    private String filename = "";
    private String resultFilename;
    private String resultPath;
    private int amount, len, track;
    private String[][] data;
    private SonicPiFormat formatter;

    /**
     * Metodi hallinnoi tarvittavien elementtien kutsuja.
     */
    public void start() {
        scanner = new Scanner(System.in);
        System.out.println("\t \t Java Music Generation");
        System.out.println("\n\n");
        while (true) {
            System.out.println("******");
            readUserInstructions();
            trie = new Trie(len);
            data = new String[amount][len];
            root = trie.getRoot();
            try {
                NoteReader notereader = new NoteReader(filename, trie, len, track);
                notereader.read();
                markovGenerator = new MarkovGenerator(notereader.getDivision());
                String[] freqArray = new String[len];

                for (int i = 0; i < len; i++) {
                    for (int j = 0; j < amount; j++) {
                        String[] s = markovGenerator.generateSequence(root, freqArray, 0);
                        data[j][i] = s[i];
                    }
                }
                formatter = new SonicPiFormat(data, resultFilename, resultPath);
            } catch (URISyntaxException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Generate another? Enter [Y] to continue, any other key to exit");
             
            if (!scanner.nextLine().equals("Y")) {
                break;
            }
        }

    }

    /**
     * Luetaan käyttäjän toiveet halutusta tiedostosta ja midi-raidasta,
     * sarjojen pituudesta ja toistojen lukumäärästä.
     */
    public void readUserInstructions() {
        System.out.println("The program reads a .csv file in the resources folder and prints generated melodies");
        System.out.println("Enter .csv filename");
        this.filename = scanner.nextLine();
        System.out.println("Enter filename for end result: ");
        this.resultFilename = scanner.nextLine();
        System.out.println("Enter result path (blank for default):");
        this.resultPath = scanner.nextLine();
        System.out.println("Enter midi track to read:");
        this.track = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter length of note sequences");
        this.len = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter amount of note sequences");
        this.amount = Integer.valueOf(scanner.nextLine());
    }

    public int getLen() {
        return len;
    }
}
