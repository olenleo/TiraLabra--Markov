package musicgenerator.note.printer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luo Sonic Pi-syntaksilla toimivan tiedoston.
 *
 * @author Leo Niemi
 */
public class SonicPiFormat {

    private String[][] data;
    private final String path;
    private File file;
    private String filename;

    public SonicPiFormat(String[][] data, String filename, String path) {
        this.data = data;
        this.filename = filename;
        if (path.isEmpty()) {
            this.path = "C:\\Users\\35850\\Documents\\SONIC PI\\Code\\" + filename + ".rb";
        } else {
            this.path = path + "\\ " + filename + ".rb";
        }
        try {
            createFile(this.path);
        } catch (IOException ex) {
            Logger.getLogger(SonicPiFormat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodi luo tiedoston ja lisää siihen luetun materiaalin ja
     * käyttökelpoisen koodin.
     *
     * @param fullPath Polku + tiedostonimi
     * @throws IOException
     */
    private void createFile(String fullPath) throws IOException {
        System.out.println(data.length);
        System.out.println(Arrays.deepToString(data));

        this.file = new File(fullPath);
        file.delete();
        file.getParentFile().mkdirs();
        file.createNewFile();
        writeData();
        writeBody();
    }

    /**
     * Kovakoodattu toimiva aloituspiste Sonic Pi:tä varten. live_loop saa nimekseen tiedostonimen useamman loopin samanaikaisen käytön yksinkertaistamiseksi.
     *
     * @throws IOException
     */
    public void writeBody() throws IOException {
        // String string = String.format("boid %s", aVariable);
        String body = String.format("\nuse_bpm 100\n"
                + "\n"
                + "rows = notes.length - 1\n"
                + "len = notes[0].length - 1\n"
                + "\n"
                + "live_loop :%s do\n"
                + "  use_synth :dtri\n"
                + "  row_select = rrand_i(0, rows)\n"
                + "  row = notes[row_select]\n"
                + "  i = 0\n\n"
                + "  len.times do \n"
                + "    note_object = row[i]\n"
                + "    puts note_object\n"
                + "    pitch = note_object[0]\n"
                + "    sustain = note_object[1]\n"
                + "    rest = note_object[2]\n"
                + "    play pitch, attack: 0, sustain: sustain, release: 0.02 ,amp: 1\n"
                + "    sleep rest\n"
                + "    i += 1\n"
                + "  end\nend", filename);
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.append(body);
        }
    }

    /**
     * Kirjoittaa nuotit @writeBody() - metodin kanssa yhteensopivaan
     * taulukkoon.
     *
     * @throws IOException
     */
    public void writeData() throws IOException {
        String noteData = "notes = \n";
        for (int i = 0; i < data.length; i++) {
            noteData += "\t[";
            for (int j = 0; j < data[0].length; j++) {
                if (j == data[0].length - 1) {
                    if (i == data.length - 1) {
                        noteData += data[i][j] + "]\n";
                    } else {
                        noteData += data[i][j] + "],\n";
                    }
                } else {
                    noteData += data[i][j] + ",";
                }
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.append(noteData);
        writer.close();
    }
}
