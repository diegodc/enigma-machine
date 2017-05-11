# enigma-machine
Implementation of the Enigma ciphering machine in Java.

Enigma models M3 and M4 are modeled with their corresponding rotors.

The code is tested against real enigma messages.

Example:

    /*  Originial example from Enigma user manual
        Machine Settings for Enigma I/M3
        Reflector:      A
        Wheel order:    II I III
        Ring positions: XMV
        Plug pairs:     AM FI NV PS TU WZ
        Key:            ABL
     */
    MessageRequest request = new MessageRequest();

    request.machineModel = "M3";
    request.reflector = "A";
    request.wheelOrder = "II I III";
    request.ringSettings = "XMV";
    request.plugboardSettings = "AM FI NV PS TU WZ";
    request.key = "ABL";
    request.plaintext = "GCDSEAHUGWTQGRKVLFGXUCALXVYMIGMMNMFDXTGNVHVRMMEVOUYFZSLRHDRRXFJWCFHUHMUNZEFRDISIKBGPMYVXUZ";

    String cipherMessage = enigmaService.cipherMessage(request);


For more usage examples go to the test class EnigmaServiceTest.