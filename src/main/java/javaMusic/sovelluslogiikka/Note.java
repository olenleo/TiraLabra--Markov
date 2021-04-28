/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaMusic.sovelluslogiikka;

/**
 *
 * @author Leo Niemi
 */
public class Note {

    private int length;
    private int pitch;
    private boolean isRest = false;

    public int getLength() {
        return length;
    }

    public int getPitch() {
        return pitch;
    }

    public boolean isRest() {
        return isRest;
    }

    public Note(int pitch, int length, boolean isRest) {
        this.length = length;
        this.pitch = pitch;
        this.isRest = isRest;
    }

    public Note(int length, boolean isRest) {
        this.length = length;
        this.isRest = isRest;
        
    }

    @Override
    public String toString() {
        return "" + this.pitch + " " + this.length + " " + this.isRest;
    }

}
