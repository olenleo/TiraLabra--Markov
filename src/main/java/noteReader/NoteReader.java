package noteReader;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import javaMusic.sovelluslogiikka.Note;

/**
 * Luokka lukee esiformatoidun .csv-tiedoston ja muuttaa sen käyttökelpoiseksi
 * informaatioksi.
 *
 *  * @author Leo Niemi
 */
public class NoteReader {

    private int[] notes = new int[127];
    ArrayDeque<Note> pino;
    private int nuottisarjanPituus = 5; // trieen tallennettavien sarjojen pituus

    public NoteReader() throws URISyntaxException, FileNotFoundException, IOException {
        this.pino = new ArrayDeque<>();

        URL res = getClass().getClassLoader().getResource("test.csv");
        File file = Paths.get(res.toURI()).toFile();

        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        FileReader f = new FileReader(file);
        BufferedReader bufferedreader = new BufferedReader(f);
        CSVReader r = new CSVReader(bufferedreader);
        String[] record;

        while ((record = r.readNext()) != null) {
            int track = Integer.valueOf(record[0]);
            if (record[2].contains("Note_")) {

                int timestamp = Integer.parseInt(record[1].trim() + 1); // + 1 koska notes[0] ilmaisee että nuotti ei ole käytössä
                String command = record[2];
                int note = Integer.valueOf(record[4].trim());
                System.out.println(Arrays.toString(record));
                if (notes[note] == 0) {
                    notes[note] = timestamp;
                } else {
                    Note lisattava = new Note(note, notes[note], timestamp);                    
                    lisaaNuottiPinoon(lisattava);
                    notes[note] = 0;
                }
            }
        }
         

}
    
    private void lisaaNuottiPinoon(Note note) {
        // jos pino on pienempi kuin sanan pituus
        if (pino.size() < nuottisarjanPituus) {
            pino.add(note);
        } else {
            // sana valmis, lisää trieen.
            pino.removeFirst();
            pino.add(note);
        }
        System.out.println("Lisätty: " + note);
    }

}
