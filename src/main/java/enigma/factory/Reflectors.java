package enigma.factory;

import enigma.configuration.Wirings;
import enigma.core.devices.Reflector;
import enigma.core.util.Letter;

/**
 * Utility class for building reflectors.
 *
 * @author diegodc 2017-04-17.
 */
public enum Reflectors {

    A(Wirings.REFLECTOR_A),
    B(Wirings.REFLECTOR_B),
    C(Wirings.REFLECTOR_C),
    ThinB(Wirings.REFLECTOR_B_THIN),
    ThinC(Wirings.REFLECTOR_C_THIN);

    private final Letter[] wiring;

    Reflectors(Letter[] wiring) {
        this.wiring = wiring;
    }

    public Reflector get() {
        return new Reflector(wiring);
    }

}
