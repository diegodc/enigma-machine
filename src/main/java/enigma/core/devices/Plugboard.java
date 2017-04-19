package enigma.core.devices;

import enigma.core.util.Letter;

import java.util.HashMap;
import java.util.Map;

/**
 * The plugboard of the enigma machine.
 * The plugboard connects letters in pairs, swapping those letters before and after the rotors.
 *
 * @author diegodc 2017-02-07.
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

    public Letter swap(Letter letter) {
        return wiring.get(letter);
    }

    public void disconnectAllPairs() {
        generateDefaultWiring();
    }

    public Plugboard connect(Letter letter1, Letter letter2) {
        disconnectPair(letter1);
        disconnectPair(letter2);
        connectPair(letter1, letter2);
        return this;
    }

    private void disconnectPair(Letter letter) {
        Letter paired = swap(letter);
        wiring.put(letter, letter);
        wiring.put(paired, paired);
    }

    private void connectPair(Letter letter, Letter connectedLetter) {
        wiring.put(letter, connectedLetter);
        wiring.put(connectedLetter, letter);
    }

}
