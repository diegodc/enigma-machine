package enigma.core.devices;

import enigma.core.util.Letter;

import java.util.HashMap;
import java.util.Map;

/**
 * The reflector of the enigma machine.
 * The reflector connects outputs of the last rotor in pairs.
 *
 * @author diegodc 2017-02-06.
 */
public class Reflector {

    private Map<Letter, Letter> wiring = new HashMap<>();

    public Reflector(Letter[] wiring) {
        loadWiring(wiring);
        validateWiring();
    }

    private void loadWiring(Letter[] wiring) {
        Letter reflectedLetter = Letter.A;
        for (Letter letter : wiring) {
            this.wiring.put(letter, reflectedLetter);
            reflectedLetter = reflectedLetter.nextLetter();
        }
    }

    private void validateWiring() {
        if (wiring.keySet().size() != 26)
            throw new MalformedWiring();
    }

    public Letter reflect(Letter letter) {
        return wiring.get(letter);
    }

}
