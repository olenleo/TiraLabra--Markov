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
     *
     * @param note Kokonaisluku 0-127
     * @param start Alkupistettä vähennetään yhdellä notereaderin tietorakenteen
     * käsittelyn helpottamiseksi
     * @param end Päätepistettä vähennetään yhdellä notereaderin tietorakenteen
     * käsittelyn helpottamiseksi
     */
    public Note(int note, int start, int end) {
        this.note = note;
        this.start = start;
        this.end = end;
        this.duration = this.end - this.start;
    }

    public int getNote() {
        return this.note;
    }

    @Override
    public String toString() {
//        int mod = this.note % 12;
//        String[] nuottinimet = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "B#"};
//        return nuottinimet[mod];
        return "new Note(" + this.note + "," + this.start + "," + this.end + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.note;
        hash = 71 * hash + this.start;
        hash = 71 * hash + this.end;
        hash = 71 * hash + this.duration;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Note other = (Note) obj;
        if (this.note != other.note) {
            return false;
        }
        if (this.start != other.start) {
            return false;
        }
        if (this.end != other.end) {
            return false;
        }
        if (this.duration != other.duration) {
            return false;
        }
        return true;
    }
    
    
}
