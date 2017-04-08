package enigma.core.devices;

import enigma.core.util.Letter;

import java.util.HashMap;
import java.util.Map;

/**
 * The plugboard of the enigma machine.
 * The plugboard connects letters in pairs, swapping those letters before and after the rotors.
 *
 * @author diegodc 2017-02-07
 */
public class Plugboard {

    private Map<Letter, Letter> wiring = new HashMap<>();

    public Plugboard() {
        generateDefaultWiring();
    }

    private void generateDefaultWiring() {
        for (Letter letter : Letter.values())
            wiring.put(letter, letter);
    }

    public Plugboard pairLetters(Letter letter, Letter pairedLetter) {
        resetPair(letter);
        resetPair(pairedLetter);
        pair(letter, pairedLetter);
        return this;
    }

    private void resetPair(Letter letter) {
        Letter paired = cipher(letter);
        wiring.put(letter, letter);
        wiring.put(paired, paired);
    }

    private void pair(Letter letter, Letter pairedLetter) {
        wiring.put(letter, pairedLetter);
        wiring.put(pairedLetter, letter);
    }

    public Letter cipher(Letter letter) {
        return wiring.get(letter);
    }

    public void reset() {
        generateDefaultWiring();
    }

}
