package enigma.service;

import enigma.core.machine.Enigma;
import enigma.factory.M3Rotors;
import enigma.factory.M4Rotors;
import enigma.factory.Reflectors;

import java.util.Arrays;

/**
 * Provides a simple service to encode messages.
 *
 * @author diegodc 2017-04-10.
 */
public class EnigmaService {

    private MessageRequest request;

    public String cipherMessage(MessageRequest messageRequest) {

        request = messageRequest;

        return buildMachine().cipherMessage(request.plaintext);
    }

    private Enigma buildMachine() {
        return build()
                .changeRingSettings(request.ringSettings)
                .setPlugboard(request.plugboardSettings)
                .changeRotorsPositions(request.key);
    }

    private Enigma build() {
        switch (request.machineModel) {
            case "M3":
                return buildM3Enigma();
            case "M4":
                return buildM4Enigma();
        }
        throw new EnigmaServiceException("Invalid enigma model: " + request.machineModel);
    }

    private Enigma buildM4Enigma() {

        String[] rotors = parseWheelOrder(request.wheelOrder, 4);

        return new Enigma(
                Reflectors.valueOf(request.reflector).get(),
                M4Rotors.valueOf(rotors[0]).get(),
                M3Rotors.valueOf(rotors[1]).get(),
                M3Rotors.valueOf(rotors[2]).get(),
                M3Rotors.valueOf(rotors[3]).get());
    }

    private Enigma buildM3Enigma() {

        String[] rotors = parseWheelOrder(request.wheelOrder, 3);

        return new Enigma(
                Reflectors.valueOf(request.reflector).get(),
                M3Rotors.valueOf(rotors[0]).get(),
                M3Rotors.valueOf(rotors[1]).get(),
                M3Rotors.valueOf(rotors[2]).get());
    }

    private String[] parseWheelOrder(String wheelOrder, int numberOfRotors) {
        String[] rotors = splitRotors(wheelOrder);

        checkNumberOfRotors(numberOfRotors, rotors);

        return rotors;
    }

    private String[] splitRotors(String wheelOrder) {
        return wheelOrder.split(" ");
    }

    private void checkNumberOfRotors(int numberOfRotors, String[] rotors) {
        if (Arrays.stream(rotors).distinct().count() != numberOfRotors)
            throw new EnigmaServiceException("Invalid number or duplicated rotors: " + Arrays.asList(rotors));
    }

}
