package javaMusic.sovelluslogiikka;

/**
 *
 * @author Leo Niemi
 */
public class Note {

    private int pitch;
    private int sustain;
    private int timeToRest;

    public int getTimeToRest() {
        return timeToRest;
    }

    public int getPitch() {
        return pitch;
    }

    public int getSustain() {
        return sustain;
    }

    /**
     * Nuotti taltioi sävelensä, kestonsa
     *
     * @param pitch
     * @param sustain
     * @param timeToRest
     */
    public Note(int pitch, int sustain, int timeToRest) {
        this.pitch = pitch;
        this.sustain = sustain;
        this.timeToRest = timeToRest;
    }
    /**
     * Soinnut ja intervallit - seuraava nuotti alkaa samanaikaisesti.
     * @param pitch
     * @param sustain 
     */
    
    public Note(int pitch, int sustain) {
        this.pitch = pitch;
        this.sustain = sustain;
        this.timeToRest = 0;
    }

    @Override
    public String toString() {
        return "" + this.pitch + " " + this.sustain + " " + this.timeToRest;
    }

}
