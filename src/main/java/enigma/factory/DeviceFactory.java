package enigma.factory;

import enigma.core.devices.NotchedRotor;
import enigma.core.devices.Reflector;
import enigma.core.devices.Rotor;

import static enigma.factory.TurnoverPositions.*;
import static enigma.factory.Wirings.*;

/**
 * Provides methods for the construction of the different rotors and reflectors.
 *
 * @author diegodc 2017-02-03
 */
public class DeviceFactory {

    public static NotchedRotor I() {
        return new NotchedRotor(ROTOR_I, ROTOR_I_NOTCH);
    }

    public static NotchedRotor II() {
        return new NotchedRotor(ROTOR_II, ROTOR_II_NOTCH);
    }

    public static NotchedRotor III() {
        return new NotchedRotor(ROTOR_III, ROTOR_III_NOTCH);
    }

    public static NotchedRotor IV() {
        return new NotchedRotor(ROTOR_IV, ROTOR_IV_NOTCH);
    }

    public static NotchedRotor V() {
        return new NotchedRotor(ROTOR_V, ROTOR_V_NOTCH);
    }

    public static NotchedRotor VI() {
        return new NotchedRotor(ROTOR_VI, ROTOR_VI_NOTCHES);
    }

    public static NotchedRotor VII() {
        return new NotchedRotor(ROTOR_VII, ROTOR_VII_NOTCHES);
    }

    public static NotchedRotor VIII() {
        return new NotchedRotor(ROTOR_VIII, ROTOR_VIII_NOTCHES);
    }

    public static Rotor beta() {
        return new Rotor(ROTOR_BETA);
    }

    public static Rotor gamma() {
        return new Rotor(ROTOR_GAMMA);
    }

    public static Reflector A() {
        return new Reflector(REFLECTOR_A);
    }

    public static Reflector B() {
        return new Reflector(REFLECTOR_B);
    }

    public static Reflector C() {
        return new Reflector(REFLECTOR_C);
    }

    public static Reflector thinB() {
        return new Reflector(REFLECTOR_B_THIN);
    }

    public static Reflector thinC() {
        return new Reflector(REFLECTOR_C_THIN);
    }

}
