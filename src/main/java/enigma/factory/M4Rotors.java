package enigma.factory;

import enigma.configuration.Wirings;
import enigma.core.devices.Rotor;
import enigma.core.util.Letter;

/**
 * Utility class for building rotors.
 *
 * @author diegodc 2017-04-17.
 */
public enum M4Rotors {

    Beta(Wirings.ROTOR_BETA),
    Gamma(Wirings.ROTOR_GAMMA);

    private final Letter[] wiring;

    M4Rotors(Letter[] wiring) {
        this.wiring = wiring;
    }

    public Rotor get() {
        return new Rotor(wiring);
    }

}
