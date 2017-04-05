package enigma.core.devices;

import enigma.core.util.Alphabet;

import java.util.HashMap;
import java.util.Map;

/**
 * The reflector of the enigma machine.
 * The reflector connects outputs of the last rotor in pairs.
 *
 * @author diegodc 2017-02-06
 */
public class Reflector {

    private Map<Alphabet, Alphabet> wiring = new HashMap<>();

    public Reflector(Alphabet[] wiring) {
        int arrayPosition = 0;
        for (Alphabet letter : Alphabet.values()) {
            this.wiring.put(letter, wiring[arrayPosition]);
            arrayPosition++;
        }
    }

    public Alphabet reflect(Alphabet absoluteLetter) {
        return wiring.get(absoluteLetter);
    }

}
