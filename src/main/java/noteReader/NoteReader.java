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

    public NoteReader() throws URISyntaxException, FileNotFoundException, IOException {

        URL res = getClass().getClassLoader().getResource("test.csv");
        File file = Paths.get(res.toURI()).toFile();

        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        FileReader f = new FileReader(file);
        BufferedReader bufferedreader = new BufferedReader(f);
        CSVReader r = new CSVReader(bufferedreader);
        String[] record;
        ArrayList<Note> notes = new ArrayList<>();
        while ((record = r.readNext()) != null) {
            
            // TODO tallennetaan nuotit pinoon? 
            if (record[2].contains("Note_")) {
                System.out.println(Arrays.toString(record));
            }
        }
    }
}
