package musicgenerator.sovelluslogiikka;

/**
 * Jokainen nuotti taltioi sävelensä, kestonsa ja seuraavan nuotin alkamispisteen.
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
     * Nuotti jota seuraa tauko.
     *
     * @param pitch
     * @param sustain 
     * @param timeToRest kääntyy Sonic Pi-toteutuksessa sleep-komennoksi eli tauoksi.
     */
    public Note(int pitch, int sustain, int timeToRest) {
        this.pitch = pitch;
        this.sustain = sustain;
        this.timeToRest = timeToRest;
    }
    /**
     * Soinnut ja intervallit muodostuvat kun moni nuotti alkaa samanaikaisesti.
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
