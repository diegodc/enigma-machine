package enigma.core.util;

/**
 * The alphabet used by the Enigma Machine.
 * This class hides all the low level details about dealing with indexes, shifts, conversions, etc.
 *
 * @author diegodc 2017-02-02.
 */
public enum Letter {

    A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;

    /**
     * Converts the given character into a Letter.
     *
     * @param character the char to be converted
     * @return a Letter that represents the given character
     */
    public static Letter fromChar(char character) {
        String letter = String.valueOf(character).toUpperCase();
        return Letter.valueOf(letter);
    }

    /**
     * Gives the next letter in the alphabet.
     *
     * Example:
     * A.nextLetter -> B
     * Z.nextLetter -> A
     *
     * @return the next Letter in the alphabet
     */
    public Letter nextLetter() {
        int nextLetterOrdinal = (this.ordinal() + 1) % 26;
        return values()[nextLetterOrdinal];
    }

    /**
     * Returns this letter as a char.
     *
     * @return the current Letter as char
     */
    public char asChar() {
        return this.toString().charAt(0);
    }

    /**
     * Shifts the letter a number of positions down (or up) the alphabet.
     *
     * @param positions the number of positions down or up the alphabet
     * @return the shifted letter
     */
    public Letter shiftBy(int positions) {
        int position = limitPosition(this.ordinal() + positions);
        return values()[position];
    }

    private int limitPosition(int offset) {
        return ((offset % 26) + 26) % 26;
    }

}
