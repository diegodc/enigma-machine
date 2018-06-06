# enigma-machine
#### Implementation of the Enigma ciphering machine in Java.

Enigma models M3 and M4 are modeled with their corresponding rotors.

The code is tested against real enigma messages.


### Try it:
[https://enigma-machine-rest.herokuapp.com/cipher](https://enigma-machine-rest.herokuapp.com/cipher)

    URL: https://enigma-machine-rest.herokuapp.com/cipher
    Method: POST
    Request Body:
        {
	        "enigmaModel" : "M3",
	        "wheels" : "IV III I",
	        "reflector" : "C",
	        "ringSettings" : "HRL",
	        "plugboard" : "AG KR EL BM OX TZ",
	        "key" : "DTU",
	        "message" : "VIEXFS"
         }




#### Code Example:

    /*  Originial example from Enigma user manual
        Machine Settings for Enigma I/M3
        Reflector:      A
        Wheel order:    II I III
        Ring positions: XMV
        Plug pairs:     AM FI NV PS TU WZ
        Key:            ABL
     */
    MessageRequest request = new MessageRequest();

    request.enigmaModel = "M3";
    request.reflector = "A";
    request.wheels = "II I III";
    request.ringSettings = "XMV";
    request.plugboard = "AM FI NV PS TU WZ";
    request.key = "ABL";
    request.message = "GCDSEAHUGWTQGRKVLFGXUCALXVYMIGMMNMFDXTGNVHVRMMEVOUYFZSLRHDRRXFJWCFHUHMUNZEFRDISIKBGPMYVXUZ";

    String cipherMessage = enigmaService.cipherMessage(request);


For more usage examples go to the test class EnigmaServiceTest.