package javaMusic.notePrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luo Sonic Pi-syntaksilla toimivan tiedoston javaMusic.rb.
 *
 * @author Leo Niemi
 */
public class SonicPiFormat {

    private String[][] data;
    private final String path;
    private File file;

    public SonicPiFormat(String[][] data) {
        this.data = data;
        this.path = "C:\\Users\\35850\\Documents\\SONIC PI\\Code\\javaMusic.rb";
        try {
            createFile(this.path);
        } catch (IOException ex) {
            Logger.getLogger(SonicPiFormat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    public void writeBody() throws IOException {
        String body = "use_bpm 180\n"
                + "rows = notes.length - 1\n"
                + "len = notes[0].length - 1\n"
                + "live_loop :test do\n"
                + "  use_synth :prophet\n"
                + "  row_select = rrand_i(0, rows)\n"
                + "  row = notes[row_select]\n"
                + "  i = 0\n"
                + "  \n"
                + "  len.times do\n"
                + "    \n"
                + "    note_object = row[i]\n"
                + "    puts note_object\n"
                + "    pitch = note_object[0]\n"
                + "    sustain = note_object[1]\n"
                + "    rest = note_object[2]\n"
                + "    \n"
                + "    if pitch > 128\n"
                + "      sleep rest\n"
                + "    else\n"
                + "      play pitch + 12, attack: 0.1, sustain: sustain, amp: 1\n"
                + "      sleep rest\n"
                + "    end\n"
                + "    i += 1\n"
                + "  end\n"
                + "end";
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.append(body);
        }
    }

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
        writer.append(' ');
        writer.append(noteData);

        writer.close();
    }
}
