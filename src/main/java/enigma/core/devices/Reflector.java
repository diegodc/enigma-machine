package enigma.core.devices;

import enigma.core.util.Letter;

import java.util.HashMap;
import java.util.Map;

/**
 * The reflector of the enigma machine.
 * The reflector connects outputs of the last rotor in pairs.
 *
 * @author diegodc 2017-02-06
 */
public class Reflector {

    private Map<Letter, Letter> wiring = new HashMap<>();

    public Reflector(Letter[] wiring) {
        int arrayPosition = 0;
        for (Letter letter : Letter.values()) {
            this.wiring.put(letter, wiring[arrayPosition]);
            arrayPosition++;
        }
    }

    public Letter reflect(Letter absoluteLetter) {
        return wiring.get(absoluteLetter);
    }

}
