package enigma.core.devices;

import enigma.core.util.Letter;

import java.util.HashMap;
import java.util.Map;

/**
 * A rotor of the Enigma machine.
 *
 * @author diegodc 2017-02-02
 */
public class Rotor {

    private Letter position = Letter.A;
    private Letter ringSetting = Letter.A;
    private Letter turnoverPosition;
    private Map<Letter, Letter> inwardWiring = new HashMap<>();
    private Map<Letter, Letter> outwardWiring = new HashMap<>();

    public Rotor(Letter[] wiring, Letter turnoverPosition) {
        loadWiringMaps(wiring);
        this.turnoverPosition = turnoverPosition;
    }

    private void loadWiringMaps(Letter[] wiring) {
        Letter letter = Letter.A;

        for (Letter substituteLetter : wiring) {
            inwardWiring.put(letter, substituteLetter);
            outwardWiring.put(substituteLetter, letter);
            letter = letter.nextLetter();
        }
    }

    public Letter getPosition() {
        return position;
    }

    public void changePositionTo(Letter letter) {
        position = letter;
    }

    public Letter getRingSetting() {
        return ringSetting;
    }

    public void ringSetting(Letter letter) {
        ringSetting = letter;
    }

    public void step() {
        position = position.nextLetter();
    }

    public boolean isAtTurnoverPosition() {
        return position == turnoverPosition;
    }

    public Letter cipherInwards(Letter absoluteLetter) {
        return cipherWith(inwardWiring, absoluteLetter);
    }

    public Letter cipherOutwards(Letter absoluteLetter) {
        return cipherWith(outwardWiring, absoluteLetter);
    }

    private Letter cipherWith(Map<Letter, Letter> wiring, Letter absoluteLetter) {
        Letter relativeLetter = toRelativeLetter(absoluteLetter);
        Letter cipherLetter = wiring.get(relativeLetter);
        return toAbsoluteLetter(cipherLetter);
    }

    private Letter toRelativeLetter(Letter absoluteLetter) {
        return absoluteLetter.shiftBy(position.ordinal() - ringSetting.ordinal());
    }

    private Letter toAbsoluteLetter(Letter relativeLetter) {
        return relativeLetter.shiftBy(ringSetting.ordinal() - position.ordinal());
    }

}
