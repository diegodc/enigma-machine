package enigma.core.devices;

import enigma.core.util.Letter;

import java.util.HashMap;
import java.util.Map;

/**
 * A rotor of the Enigma machineModel. The rotor ciphers each letter according to its wiring,
 * position and ring setting.
 *
 * This is a simple rotor without turnover notch.
 *
 * More information: https://en.wikipedia.org/wiki/Enigma_rotor_details
 *
 * @author diegodc 2017-02-02.
 */
public class Rotor {

    private Letter position = Letter.A;
    private Letter ring = Letter.A;

    private Map<Letter, Letter> inwardWiring = new HashMap<>();
    private Map<Letter, Letter> outwardWiring = new HashMap<>();

    /**
     * Creates a new rotor with the given wiring.
     * Initially the rotor is set in 'A' position and ring setting is also 'A'.
     *
     * Wiring format example from Rotor I (Enigma I, M3 and M4):
     * {E,K,M,F,L,G,D,Q,V,Z,N,T,O,W,Y,H,X,U,S,P,A,I,B,R,C,J}
     *
     * 'A' is encoded as 'E'
     * 'B' is encoded as 'K'
     * 'C' is encoded as 'M'
     * 'D' is encoded as 'F'
     * and so on...
     *
     * @param wiring the wiring of the rotor
     */
    public Rotor(Letter[] wiring) {
        loadWiringMaps(wiring);
        validateWiring();
    }

    /**
     * Loads the internal wiring map.
     *
     * @param wiring the wiring of the rotor
     */
    private void loadWiringMaps(Letter[] wiring) {
        Letter letter = Letter.A;
        for (Letter substituteLetter : wiring) {
            inwardWiring.put(letter, substituteLetter);
            outwardWiring.put(substituteLetter, letter);
            letter = letter.nextLetter();
        }
    }

    /**
     * Checks if the wiring is complete.
     * The outward wiring is used because the keySet is the same
     * as the wiring given in the constructor.
     */
    private void validateWiring() {
        if (outwardWiring.keySet().size() != 26)
            throw new MalformedWiring();
    }

    public Letter currentPosition() {
        return position;
    }

    public void changePosition(Letter letter) {
        position = letter;
    }

    public Letter ringSetting() {
        return ring;
    }

    public void changeRingSetting(Letter letter) {
        ring = letter;
    }

    public void step() {
        position = position.nextLetter();
    }

    /**
     * Ciphers the given letter inwards. This is from the entry wheel into the rotors.
     * Both the given letter and the encoded letter are in the "absolute position".
     * Meaning the position of the letter in the entry wheel.
     *
     * @param letter the letter to be encoded
     * @return the encoded letter
     */
    public Letter cipherInwards(Letter letter) {
        return cipherWith(inwardWiring, letter);
    }

    /**
     * Ciphers the given letter outwards. This is from the reflector back out through the rotors .
     * Both the given letter and the encoded letter are in the "absolute position".
     * Meaning the position of the letter in the entry wheel.
     *
     * @param letter the letter to be encoded
     * @return the encoded letter
     */
    public Letter cipherOutwards(Letter letter) {
        return cipherWith(outwardWiring, letter);
    }

    /**
     * Encodes the letter using the given wiring.
     *
     * @param wiring the wiring used to encode
     * @param letter the letter to be encoded
     * @return the encoded letter
     */
    private Letter cipherWith(Map<Letter, Letter> wiring, Letter letter) {
        Letter relativeLetter = toRelativeLetter(letter);
        Letter cipherLetter = wiring.get(relativeLetter);
        return toAbsoluteLetter(cipherLetter);
    }

    /**
     * Translates the letter from the "absolute position" (position in the entry wheel)
     * to the relative position where it enters the rotor.
     *
     * @param absoluteLetter letter in absolute position
     * @return the "relative" letter
     */
    private Letter toRelativeLetter(Letter absoluteLetter) {
        return absoluteLetter.shiftBy(position.ordinal() - ring.ordinal());
    }

    /**
     * Translates the letter from the relative position as it exits the rotor,
     * to the absolute position (position in the entry wheel).
     *
     * @param relativeLetter letter in relative position
     * @return the "absolute" letter
     */
    private Letter toAbsoluteLetter(Letter relativeLetter) {
        return relativeLetter.shiftBy(ring.ordinal() - position.ordinal());
    }

}
