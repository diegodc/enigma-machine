# enigma-machine
#### API for encrypting and decrypting messages with the [Enigma Machine](http://en.wikipedia.org/wiki/Enigma_machine)

+ Enigma models M3 and M4 are modeled with their corresponding rotors.

+ The code is tested against real enigma messages.

###

[https://enigma-machine-rest.herokuapp.com/messages](https://enigma-machine-rest.herokuapp.com/messages)


    URL: https://enigma-machine-rest.herokuapp.com/messages
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

