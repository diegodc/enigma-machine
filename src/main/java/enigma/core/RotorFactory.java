package enigma.core;

import enigma.core.devices.DoubleNotchRotor;
import enigma.core.devices.Reflector;
import enigma.core.devices.Rotor;
import enigma.core.util.Alphabet;

import static enigma.core.util.Alphabet.*;

/**
 * Provides methods for the construction of the different rotors.
 *
 * @author diegodc 2017-02-03
 */
public class RotorFactory {

    private static final Alphabet[] REFLECTOR_A_WIRING = {E,J,M,Z,A,L,Y,X,V,B,W,F,C,R,Q,U,O,N,T,S,P,I,K,H,G,D};
    private static final Alphabet[] REFLECTOR_B_WIRING = {Y,R,U,H,Q,S,L,D,P,X,N,G,O,K,M,I,E,B,F,Z,C,W,V,J,A,T};
    private static final Alphabet[] REFLECTOR_C_WIRING = {F,V,P,J,I,A,O,Y,E,D,R,Z,X,W,G,C,T,K,U,Q,S,B,N,M,H,L};
    private static final Alphabet[] REFLECTOR_THIN_B_WIRING = {E,N,K,Q,A,U,Y,W,J,I,C,O,P,B,L,M,D,X,Z,V,F,T,H,R,G,S};
    private static final Alphabet[] REFLECTOR_THIN_C_WIRING = {R,D,O,B,J,N,T,K,V,E,H,M,L,F,C,W,Z,A,X,G,Y,I,P,S,U,Q};

    private static final Alphabet[] ROTOR_I_WIRING = {E,K,M,F,L,G,D,Q,V,Z,N,T,O,W,Y,H,X,U,S,P,A,I,B,R,C,J};
    private static final Alphabet[] ROTOR_II_WIRING = {A,J,D,K,S,I,R,U,X,B,L,H,W,T,M,C,Q,G,Z,N,P,Y,F,V,O,E};
    private static final Alphabet[] ROTOR_III_WIRING = {B,D,F,H,J,L,C,P,R,T,X,V,Z,N,Y,E,I,W,G,A,K,M,U,S,Q,O};
    private static final Alphabet[] ROTOR_IV_WIRING = {E,S,O,V,P,Z,J,A,Y,Q,U,I,R,H,X,L,N,F,T,G,K,D,C,M,W,B};
    private static final Alphabet[] ROTOR_V_WIRING = {V,Z,B,R,G,I,T,Y,U,P,S,D,N,H,L,X,A,W,M,J,Q,O,F,E,C,K};
    private static final Alphabet[] ROTOR_VI_WIRING = {J,P,G,V,O,U,M,F,Y,Q,B,E,N,H,Z,R,D,K,A,S,X,L,I,C,T,W};
    private static final Alphabet[] ROTOR_VII_WIRING = {N,Z,J,H,G,R,C,X,M,Y,S,W,B,O,U,F,A,I,V,L,P,E,K,Q,D,T};
    private static final Alphabet[] ROTOR_VIII_WIRING = {F,K,Q,H,T,L,X,O,C,B,J,S,P,D,Z,R,A,M,E,W,N,I,U,Y,G,V};
    private static final Alphabet[] ROTOR_BETA_WIRING = {L,E,Y,J,V,C,N,I,X,W,P,B,Q,M,D,R,T,A,K,Z,G,F,U,H,O,S};
    private static final Alphabet[] ROTOR_GAMMA_WIRING = {F,S,O,K,A,N,U,E,R,H,M,B,T,I,Y,C,W,L,Q,P,Z,X,V,G,J,D};

    private static final Alphabet ROTOR_I_NOTCH = Q;
    private static final Alphabet ROTOR_II_NOTCH = E;
    private static final Alphabet ROTOR_III_NOTCH = V;
    private static final Alphabet ROTOR_IV_NOTCH = J;
    private static final Alphabet ROTOR_V_NOTCH = Z;
    private static final Alphabet[] ROTOR_VI_NOTCHES = {M,Z};
    private static final Alphabet[] ROTOR_VII_NOTCHES = {M,Z};
    private static final Alphabet[] ROTOR_VIII_NOTCHES = {M,Z};

    public static Rotor rotorI() {
        return new Rotor(ROTOR_I_WIRING, ROTOR_I_NOTCH);
    }

    public static Rotor rotorII() {
        return new Rotor(ROTOR_II_WIRING, ROTOR_II_NOTCH);
    }

    public static Rotor rotorIII() {
        return new Rotor(ROTOR_III_WIRING, ROTOR_III_NOTCH);
    }

    public static Rotor rotorIV() {
        return new Rotor(ROTOR_IV_WIRING, ROTOR_IV_NOTCH);
    }

    public static Rotor rotorV() {
        return new Rotor(ROTOR_V_WIRING, ROTOR_V_NOTCH);
    }

    public static Rotor rotorVI() {
        return new DoubleNotchRotor(ROTOR_VI_WIRING, ROTOR_VI_NOTCHES);
    }

    public static Rotor rotorVII() {
        return new DoubleNotchRotor(ROTOR_VII_WIRING, ROTOR_VII_NOTCHES);
    }

    public static Rotor rotorVIII() {
        return new DoubleNotchRotor(ROTOR_VIII_WIRING, ROTOR_VIII_NOTCHES);
    }

    public static Rotor rotorBetta() {
        return new Rotor(ROTOR_BETA_WIRING, null);
    }

    public static Rotor rotorGamma() {
        return new Rotor(ROTOR_GAMMA_WIRING, null);
    }

    public static Reflector reflectorA() {
        return new Reflector(REFLECTOR_A_WIRING);
    }

    public static Reflector reflectorB() {
        return new Reflector(REFLECTOR_B_WIRING);
    }

    public static Reflector reflectorC() {
        return new Reflector(REFLECTOR_C_WIRING);
    }

    public static Reflector reflectorThinB() {
        return new Reflector(REFLECTOR_THIN_B_WIRING);
    }

    public static Reflector reflectorThinC() {
        return new Reflector(REFLECTOR_THIN_C_WIRING);
    }

}
