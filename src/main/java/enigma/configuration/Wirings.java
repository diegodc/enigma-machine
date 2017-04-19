package enigma.configuration;

import enigma.core.util.Letter;

import static enigma.core.util.Letter.*;

/**
 * Wiring configuration of the rotors and reflectors.
 *
 * The letters are listed as connected to alphabet order.
 * If the first letter of a rotor is E, this means that the A is wired to the E.
 * This does not mean that E is wired to A.
 *
 * This looped wiring is only the case with the reflectors.
 *
 * @author diegodc 2017-04-07.
 */
public class Wirings {

    public static final Letter[] REFLECTOR_A = {E,J,M,Z,A,L,Y,X,V,B,W,F,C,R,Q,U,O,N,T,S,P,I,K,H,G,D};
    public static final Letter[] REFLECTOR_B = {Y,R,U,H,Q,S,L,D,P,X,N,G,O,K,M,I,E,B,F,Z,C,W,V,J,A,T};
    public static final Letter[] REFLECTOR_C = {F,V,P,J,I,A,O,Y,E,D,R,Z,X,W,G,C,T,K,U,Q,S,B,N,M,H,L};
    public static final Letter[] REFLECTOR_B_THIN = {E,N,K,Q,A,U,Y,W,J,I,C,O,P,B,L,M,D,X,Z,V,F,T,H,R,G,S};
    public static final Letter[] REFLECTOR_C_THIN = {R,D,O,B,J,N,T,K,V,E,H,M,L,F,C,W,Z,A,X,G,Y,I,P,S,U,Q};

    public static final Letter[] ROTOR_I = {E,K,M,F,L,G,D,Q,V,Z,N,T,O,W,Y,H,X,U,S,P,A,I,B,R,C,J};
    public static final Letter[] ROTOR_II = {A,J,D,K,S,I,R,U,X,B,L,H,W,T,M,C,Q,G,Z,N,P,Y,F,V,O,E};
    public static final Letter[] ROTOR_III = {B,D,F,H,J,L,C,P,R,T,X,V,Z,N,Y,E,I,W,G,A,K,M,U,S,Q,O};
    public static final Letter[] ROTOR_IV = {E,S,O,V,P,Z,J,A,Y,Q,U,I,R,H,X,L,N,F,T,G,K,D,C,M,W,B};
    public static final Letter[] ROTOR_V = {V,Z,B,R,G,I,T,Y,U,P,S,D,N,H,L,X,A,W,M,J,Q,O,F,E,C,K};
    public static final Letter[] ROTOR_VI = {J,P,G,V,O,U,M,F,Y,Q,B,E,N,H,Z,R,D,K,A,S,X,L,I,C,T,W};
    public static final Letter[] ROTOR_VII = {N,Z,J,H,G,R,C,X,M,Y,S,W,B,O,U,F,A,I,V,L,P,E,K,Q,D,T};
    public static final Letter[] ROTOR_VIII = {F,K,Q,H,T,L,X,O,C,B,J,S,P,D,Z,R,A,M,E,W,N,I,U,Y,G,V};
    public static final Letter[] ROTOR_BETA = {L,E,Y,J,V,C,N,I,X,W,P,B,Q,M,D,R,T,A,K,Z,G,F,U,H,O,S};
    public static final Letter[] ROTOR_GAMMA = {F,S,O,K,A,N,U,E,R,H,M,B,T,I,Y,C,W,L,Q,P,Z,X,V,G,J,D};

}
