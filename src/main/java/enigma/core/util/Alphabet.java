package enigma.core.util;

/**
 * Alphabet used by the Enigma machine.
 *
 * @author diegodc 2017-02-02
 */
public enum Alphabet {

    A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;

    public Alphabet nextLetter() {
        int nextLetterOrdinal = (this.ordinal() + 1) % 26;
        return values()[nextLetterOrdinal];
    }

    public Alphabet offsetBy(int offset) {
        int offsetPosition = limitOffset(this.ordinal() + offset);
        return values()[offsetPosition];
    }

    private int limitOffset(int offset) {
        return ((offset % 26) + 26) % 26;
    }

    public static Alphabet fromChar(char character) {
        String ch = String.valueOf(character);
        return Alphabet.valueOf(ch.toUpperCase());
    }

    public char asChar() {
        return this.toString().charAt(0);
    }

}
