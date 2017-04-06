# enigma-machine
Implementation of the Enigma ciphering machine in Java.

Enigma models M3 and M4 are modeled with their corresponding rotors.

The code is tested against real enigma messages.

Example:

    /*  Machine Settings for Enigma I/M3
        Reflector:      A
        Wheel order:    II I III
        Ring positions: X M V
        Plug pairs: 	  AM FI NV PS TU WZ
        Key =           A B L
    */
    enigma = new Enigma(reflectorA(), rotorII(), rotorI(), rotorIII())
             .changeRingSettings("XMV")
             .changeRotorsPositions("ABL")
             .setPlugboard("AM FI NV PS TU WZ");

    String message = "GCDSEAHUGWTQGRKVLFGXUCALXVYMIGMMNMFDXTGNVHVRMMEVOUYFZSLRHDRRXFJWCFHUHMUNZEFRDISIKBGPMYVXUZ";

    enigma.cipherMessage(message)


For more usage examples go to the test class EnigmaTest.
