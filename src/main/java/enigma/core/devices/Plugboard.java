package enigma.core.devices;

import enigma.core.util.Alphabet;

import java.util.HashMap;
import java.util.Map;

/**
 * The plugboard of the enigma machine.
 * The plugboard connects letters in pairs, swapping those letters before and after the rotors.
 *
 * @author diegodc 2017-02-07
 */
public class Plugboard {

    private Map<Alphabet, Alphabet> wiring = new HashMap<>();

    public Plugboard() {
        generateDefaultWiring();
    }

    private void generateDefaultWiring() {
        for (Alphabet letter : Alphabet.values())
            wiring.put(letter, letter);
    }

    public Plugboard pairLetters(Alphabet letter, Alphabet pairedLetter) {
        resetPair(letter);
        resetPair(pairedLetter);
        pair(letter, pairedLetter);
        return this;
    }

    private void resetPair(Alphabet letter) {
        Alphabet paired = cipher(letter);
        wiring.put(letter, letter);
        wiring.put(paired, paired);
    }

    private void pair(Alphabet letter, Alphabet pairedLetter) {
        wiring.put(letter, pairedLetter);
        wiring.put(pairedLetter, letter);
    }

    public Alphabet cipher(Alphabet letter) {
        return wiring.get(letter);
    }

    public void reset() {
        generateDefaultWiring();
    }

}
