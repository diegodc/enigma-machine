package enigma.service;

import enigma.core.machine.OriginalMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static enigma.service.EnigmaModel.M3;
import static enigma.service.EnigmaModel.M4;
import static enigma.service.ReflectorModel.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * EnigmaServiceTest
 *
 * @author diegodc 2017-04-10.
 */
class EnigmaServiceTest {

    private EnigmaService enigmaService;

    @BeforeEach
    void setUp() {
        enigmaService = new EnigmaService();
    }

    @Test
    void enigmaUserManualExample() {
        /*  Enigma user manual example
            Machine Settings for Enigma I/M3
            Reflector:      A
            Wheel order:    II I III
            Ring positions: XMV
            Plug pairs:     AM FI NV PS TU WZ
            Key:            ABL
         */
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.enigmaModel = M3;
        messageRequest.reflector = A;
        messageRequest.wheels = "II I III";
        messageRequest.ringSettings = "XMV";
        messageRequest.plugboard = "AM FI NV PS TU WZ";
        messageRequest.key = "ABL";
        messageRequest.message = OriginalMessages.ENIGMA_MANUAL_PLAINTEXT;

        String cipherMessage = enigmaService.cipherMessage(messageRequest).cipheredMessage;
        String expected = OriginalMessages.ENIGMA_MANUAL_CIPHERTEXT;
        assertEquals(expected, cipherMessage);
    }

    @Test
    void originalMessageTestOperationBarbarossa() {
        /*  Operation Barbarossa, 1941
            Sent from the Russian front on 7th July 1941.
            Machine Settings for Enigma I/M3
            Reflector:      B
            Wheel order:    II IV V
            Ring positions: BUL
            Plug pairs:     AV BS CG DL FU HZ IN KM OW RX

            Part One Key: BLA
            Part Two Key: LSD
         */
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.enigmaModel = M3;
        messageRequest.reflector = B;
        messageRequest.wheels = "II IV V";
        messageRequest.ringSettings = "BUL";
        messageRequest.plugboard = "AV BS CG DL FU HZ IN KM OW RX";
        messageRequest.key = "BLA";
        messageRequest.message = OriginalMessages.OPERATION_BARBAROSSA_PART1_PLAINTEXT;

        String cipherMessagePartI = enigmaService.cipherMessage(messageRequest).cipheredMessage;
        String expectedPartI = OriginalMessages.OPERATION_BARBAROSSA_PART1_CIPHERTEXT;
        assertEquals(expectedPartI, cipherMessagePartI);

        messageRequest.key = "LSD";
        messageRequest.message = OriginalMessages.OPERATION_BARBAROSSA_PART2_PLAINTEXT;

        String cipherMessagePartII = enigmaService.cipherMessage(messageRequest).cipheredMessage;
        String expectedPartII = OriginalMessages.OPERATION_BARBAROSSA_PART2_CIPHERTEXT;
        assertEquals(expectedPartII, cipherMessagePartII);
    }

    @Test
    void originalMessageTestNorrkoping() {
        /*  Norrkoping Enigma M3 intercept Page 81
            Machine Settings for Enigma I/M3
            Reflector:      B
            Wheel order:    VII VI V
            Ring positions: AXP
            Plug pairs:     AV BF DR IM OS WY
            Key:            AQO
         */
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.enigmaModel = M3;
        messageRequest.reflector = B;
        messageRequest.wheels = "VII VI V";
        messageRequest.ringSettings = "AXP";
        messageRequest.plugboard = "AV BF DR IM OS WY";
        messageRequest.key = "AQO";
        messageRequest.message = OriginalMessages.NORRKOPING_PLAINTEXT;

        String cipherMessage = enigmaService.cipherMessage(messageRequest).cipheredMessage;
        String expected = OriginalMessages.NORRKOPING_CIPHERTEXT;
        assertEquals(expected, cipherMessage);
    }

    @Test
    void navalEnigmaOriginalMessageTestScharnhorst() {
        /*  Scharnhorst (Konteradmiral Erich Bey), 1943
            Machine Settings for Enigma M3
            Reflector:      B
            Wheel order:    III VI VIII
            Ring positions: AHM
            Plug pairs:     AN EZ HK IJ LR MQ OT PV SW UX
            Key:            UZV
         */
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.enigmaModel = M3;
        messageRequest.reflector = B;
        messageRequest.wheels = "III VI VIII";
        messageRequest.ringSettings = "AHM";
        messageRequest.plugboard = "AN EZ HK IJ LR MQ OT PV SW UX";
        messageRequest.key = "UZV";
        messageRequest.message = OriginalMessages.SCHARNHORST_PLAINTEXT;

        String cipherMessage = enigmaService.cipherMessage(messageRequest).cipheredMessage;
        String expected = OriginalMessages.SCHARNHORST_CIPHERTEXT;
        assertEquals(expected, cipherMessage);
    }

    @Test
    void navalEnigmaOriginalMessageTestU264() {
        /*  U-264 (Kapitänleutnant Hartwig Looks), 1942
            Machine Settings for Enigma M4
            Reflector:      Thin B
            Wheel order:    B II IV I
            Ring positions: A A A V
            Plug pairs:     AT BL DF GJ HM NW OP QY RZ VX
            Key:            V J N A
         */
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.enigmaModel = M4;
        messageRequest.reflector = ThinB;
        messageRequest.wheels = "Beta II IV I";
        messageRequest.ringSettings = "AAAV";
        messageRequest.plugboard = "AT BL DF GJ HM NW OP QY RZ VX";
        messageRequest.key = "VJNA";
        messageRequest.message = OriginalMessages.U264_MESSAGE_PLAINTEXT;

        String cipherMessage = enigmaService.cipherMessage(messageRequest).cipheredMessage;
        String expected = OriginalMessages.U264_MESSAGE_CIPHERTEXT;
        assertEquals(expected, cipherMessage);
    }

    @Test
    void navalEnigmaOriginalMessageTestDonitzMay1945() {
        /*  Message from Dönitz - 1 May 1945
            Machine Settings for Enigma M4
            Reflector:      Thin C
            Wheel order:    B V VI VIII
            Ring positions: E P E L
            Plug pairs:     AE BF CM DQ HU JN LX PR SZ VW
            Key:            C D S Z
         */
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.enigmaModel = M4;
        messageRequest.reflector = ThinC;
        messageRequest.wheels = "Beta V VI VIII";
        messageRequest.ringSettings = "EPEL";
        messageRequest.plugboard = "AE BF CM DQ HU JN LX PR SZ VW";
        messageRequest.key = "CDSZ";
        messageRequest.message = OriginalMessages.DONITZ_MESSAGE_PLAINTEXT;

        String cipherMessage = enigmaService.cipherMessage(messageRequest).cipheredMessage;
        String expected = OriginalMessages.DONITZ_MESSAGE_CIPHERTEXT;
        assertEquals(expected, cipherMessage);
    }

    @Test
    void navalEnigmaOriginalMessageTestU534() {
        /*  U-534 P1030659
            Machine Settings for Enigma M4
            Reflector:      Thin B
            Wheel order:    G IV III VIII
            Ring positions: A A C U
            Plug pairs:     CH EJ NV OU TY LG SZ PK DI QB
            Key:            Y V O S
         */
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.enigmaModel = M4;
        messageRequest.reflector = ThinB;
        messageRequest.wheels = "Gamma IV III VIII";
        messageRequest.ringSettings = "AACU";
        messageRequest.plugboard = "CH EJ NV OU TY LG SZ PK DI QB";
        messageRequest.key = "YVOS";
        messageRequest.message = OriginalMessages.U534_MESSAGE_PLAINTEXT;

        String cipherMessage = enigmaService.cipherMessage(messageRequest).cipheredMessage;
        String expected = OriginalMessages.U534_MESSAGE_CIPHERTEXT;
        assertEquals(expected, cipherMessage);
    }

}