package enigma.core.devices;

import enigma.core.util.Alphabet;

import java.util.HashMap;
import java.util.Map;

/**
 * A rotor of the Enigma machine.
 *
 * @author diegodc 2017-02-02
 */
public class Rotor {

    private Alphabet position = Alphabet.A;
    private Alphabet ringSetting = Alphabet.A;
    private Alphabet turnoverPosition;
    private Map<Alphabet, Alphabet> inwardWiring = new HashMap<>();
    private Map<Alphabet, Alphabet> outwardWiring = new HashMap<>();

    public Rotor(Alphabet[] wiring, Alphabet turnoverPosition) {
        loadWiringMaps(wiring);
        this.turnoverPosition = turnoverPosition;
    }

    private void loadWiringMaps(Alphabet[] wiring) {
        Alphabet letter = Alphabet.A;

        for (Alphabet substituteLetter : wiring) {
            inwardWiring.put(letter, substituteLetter);
            outwardWiring.put(substituteLetter, letter);
            letter = letter.nextLetter();
        }
    }

    public Alphabet getPosition() {
        return position;
    }

    public void changePositionTo(Alphabet letter) {
        position = letter;
    }

    public Alphabet getRingSetting() {
        return ringSetting;
    }

    public void ringSetting(Alphabet letter) {
        ringSetting = letter;
    }

    public void step() {
        position = position.nextLetter();
    }

    public boolean isAtTurnoverPosition() {
        return position == turnoverPosition;
    }

    public Alphabet cipherInwards(Alphabet absoluteLetter) {
        return cipherWith(inwardWiring, absoluteLetter);
    }

    public Alphabet cipherOutwards(Alphabet absoluteLetter) {
        return cipherWith(outwardWiring, absoluteLetter);
    }

    private Alphabet cipherWith(Map<Alphabet, Alphabet> wiring, Alphabet absoluteLetter) {
        Alphabet relativeLetter = toRelativeLetter(absoluteLetter);
        Alphabet cipherLetter = wiring.get(relativeLetter);
        return toAbsoluteLetter(cipherLetter);
    }

    private Alphabet toRelativeLetter(Alphabet absoluteLetter) {
        return absoluteLetter.offsetBy(position.ordinal() - ringSetting.ordinal());
    }

    private Alphabet toAbsoluteLetter(Alphabet relativeLetter) {
        return relativeLetter.offsetBy(ringSetting.ordinal() - position.ordinal());
    }

}
