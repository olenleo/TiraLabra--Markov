/*
 */
package javaMusic.sovelluslogiikka;

/**
 * Placeholder; Jatkossa tämä luokka hallinnoi nuotti-olion muuttujia.
 *
 * @author Leo Niemi
 */
public class Note {

    private int note;
    private int start;
    private int end;
    private int duration;

    public Note(int note, int start, int end) {
        this.start = start;
        this.end = end;
        this.duration = this.end - this.start;
    }

    public int getNote() {
        return this.note;
    }

    @Override
    public String toString() {
        return "";
    }
}
