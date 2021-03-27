/*
 */
package javaMusic.sovelluslogiikka;

/**
 * Placeholder; Jatkossa tämä luokka hallinnoi nuotti-olion muuttujia.
 * @author Leo Niemi
 */
public class Note {
    
    private int note;
    private double duration;
    
    
    public Note(int note, double duration) {
        this.note = note;
        this.duration = duration;
    }
    
    public int getNote() {
        return this.note;
    }
    
    public double getDuration() {
        return this.duration;
    }
    @Override
    public String toString() {
        return "<" + this.note + "," + this.duration + ">";
    }
}
