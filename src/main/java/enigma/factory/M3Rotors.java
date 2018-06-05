package enigma.factory;

import enigma.configuration.TurnoverPositions;
import enigma.configuration.Wirings;
import enigma.core.devices.NotchedRotor;
import enigma.core.util.Letter;

/**
 * Utility class for building rotors.
 *
 * @author diegodc 2017-04-13.
 */
public enum M3Rotors {

    I(Wirings.ROTOR_I, TurnoverPositions.ROTOR_I_NOTCH),
    II(Wirings.ROTOR_II, TurnoverPositions.ROTOR_II_NOTCH),
    III(Wirings.ROTOR_III, TurnoverPositions.ROTOR_III_NOTCH),
    IV(Wirings.ROTOR_IV, TurnoverPositions.ROTOR_IV_NOTCH),
    V(Wirings.ROTOR_V, TurnoverPositions.ROTOR_V_NOTCH),
    VI(Wirings.ROTOR_VI, TurnoverPositions.ROTOR_VI_NOTCHES),
    VII(Wirings.ROTOR_VII, TurnoverPositions.ROTOR_VII_NOTCHES),
    VIII(Wirings.ROTOR_VIII, TurnoverPositions.ROTOR_VIII_NOTCHES);

    private final Letter[] wiring;
    private final Letter[] notches;

    M3Rotors(Letter[] wiring, Letter[] notches) {
        this.wiring = wiring;
        this.notches = notches;
    }

    public NotchedRotor get() {
        return new NotchedRotor(wiring, notches);
    }

}
