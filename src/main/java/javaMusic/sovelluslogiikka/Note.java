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

    /**
     * Konstruktori sisältää nuotin midi-arvon ja alku- sekä päätepisteet.
     * @param note Kokonaisluku 0-127
     * @param start Alkupistettä vähennetään yhdellä notereaderin tietorakenteen käsittelyn helpottamiseksi
     * @param end Päätepistettä vähennetään yhdellä notereaderin tietorakenteen käsittelyn helpottamiseksi
     */
    public Note(int note, int start, int end) {
        this.note = note;
        this.start = start - 1;
        this.end = end - 1;
        this.duration = this.end - this.start;
    }


    public int getNote() {
        return this.note;
    }

    @Override
    public String toString() {
        return "" + this.note + " " + this.start  + " - " + this.end;
    }
}
