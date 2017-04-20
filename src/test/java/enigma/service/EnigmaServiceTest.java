package enigma.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * EnigmaServiceTest
 *
 * @author diegodc 2017-04-10.
 */
class EnigmaServiceTest {

    EnigmaService enigmaService;

    @BeforeEach
    void setUp() {
        enigmaService = new EnigmaService();
    }

    @Test
    void testEnigmaM3Message() {
        /*  Machine Settings for Enigma I/M3
            Reflector: 	    A
            Wheel order: 	II I III
            Ring positions: XMV
            Plug pairs: 	AM FI NV PS TU WZ
            Key = ABL
         */
        String plainText = "GCDSEAHUGWTQGRKVLFGXUCALXVYMIGMMNMFDXTGNV" +
                "HVRMMEVOUYFZSLRHDRRXFJWCFHUHMUNZEFRDISIKBGPMYVXUZ";

        MessageRequest messageRequest = new MessageRequest();

        messageRequest.machineModel = "M3";
        messageRequest.reflector = "A";
        messageRequest.rotors = "II I III";
        messageRequest.ringSettings = "XMV";
        messageRequest.plugboardSettings = "AM FI NV PS TU WZ";
        messageRequest.key = "ABL";
        messageRequest.text = plainText;

        String message = enigmaService.cipherMessage(messageRequest);

        String cipherText = "FEINDLIQEINFANTERIEKOLONNEBEOBAQTETXANFA" +
                "NGSUEDAUSGANGBAERWALDEXENDEDREIKMOSTWAERTSNEUSTADT";

        assertEquals(cipherText, message);
    }

    @Test
    void testEnigmaM4Message() {
        /*  U-264 (Kapitänleutnant Hartwig Looks), 1942
            Machine Settings for Enigma M4
            Reflector: 	    Thin B
            Wheel order:    B II IV I
            Ring positions:	A A A V
            Plug pairs: 	AT BL DF GJ HM NW OP QY RZ VX
            Key:            V J N A
         */
        String plainText = "NCZWVUSXPNYMINHZXMQXSFWXWLKJAHSHNMCOCCAKUQPMKCSMHKSEINJUSBLKIOSXCKUBHMLLXCSJUSRRDV" +
                "KOHULXWCCBGVLIYXEOAHXRHKKFVDREWEZLXOBAFGYUJQUKGRTVUKAMEURBVEKSUHHVOYHABCJWMAKLFKLMYFVNRIZRVVR" +
                "TKOFDANJMOLBGFFLEOPRGTFLVRHOWOPBEKVWMUQFMPWPARMFHAGKXIIBG";

        MessageRequest messageRequest = new MessageRequest();

        messageRequest.machineModel = "M4";
        messageRequest.reflector = "ThinB";
        messageRequest.rotors = "B II IV I";
        messageRequest.ringSettings = "AAAV";
        messageRequest.plugboardSettings = "AT BL DF GJ HM NW OP QY RZ VX";
        messageRequest.key = "VJNA";
        messageRequest.text = plainText;

        String message = enigmaService.cipherMessage(messageRequest);

        String cipherText = "VONVONJLOOKSJHFFTTTEINSEINSDREIZWOYYQNNSNEUNINHALTXXBEIANGRIFFUNTERWASSERGEDRUECK" +
                "TYWABOSXLETZTERGEGNERSTANDNULACHTDREINULUHRMARQUANTONJOTANEUNACHTSEYHSDREIYZWOZWONULGRADYACHT" +
                "SMYSTOSSENACHXEKNSVIERMBFAELLTYNNNNNNOOOVIERYSICHTEINSNULL";

        assertEquals(cipherText, message);
    }

    @Test
    void invalidEnigmaModels_ShouldThrowException() {
        MessageRequest message = getDefaultM3Message();

        message.machineModel = "I";
        assertThrows(EnigmaService.InvalidEnigmaMessage.class, ()-> enigmaService.cipherMessage(message));

        message.machineModel = "";
        assertThrows(EnigmaService.InvalidEnigmaMessage.class, ()-> enigmaService.cipherMessage(message));

        message.machineModel = null;
        assertThrows(EnigmaService.InvalidEnigmaMessage.class, ()-> enigmaService.cipherMessage(message));
    }

    @Test
    void invalidReflectorType_ShouldThrowException() {
        MessageRequest m3Message = getDefaultM3Message();

        m3Message.reflector = "D";
        assertThrows(EnigmaService.InvalidEnigmaMessage.class, ()-> enigmaService.cipherMessage(m3Message));

        m3Message.reflector = "";
        assertThrows(EnigmaService.InvalidEnigmaMessage.class, ()-> enigmaService.cipherMessage(m3Message));

        m3Message.reflector = null;
        assertThrows(EnigmaService.InvalidEnigmaMessage.class, ()-> enigmaService.cipherMessage(m3Message));
    }

    @Test
    void invalidRotorsType_ShouldThrowException() {
        MessageRequest m3Message = getDefaultM3Message();

        m3Message.rotors = "A";
        assertThrows(EnigmaService.InvalidEnigmaMessage.class, ()-> enigmaService.cipherMessage(m3Message));

    }

    private MessageRequest getDefaultM3Message() {
        MessageRequest messageRequest = new MessageRequest();

        messageRequest.machineModel = "M3";
        messageRequest.reflector = "A";
        messageRequest.rotors = "I II III";
        messageRequest.ringSettings = "AAA";
        messageRequest.plugboardSettings = "AB";
        messageRequest.key = "AAA";
        messageRequest.text = "TEST";

        return messageRequest;
    }

    private MessageRequest getDefaultM4Message() {
        MessageRequest messageRequest = new MessageRequest();

        messageRequest.machineModel = "M4";
        messageRequest.reflector = "thinB";
        messageRequest.rotors = "B I II III";
        messageRequest.ringSettings = "AAAA";
        messageRequest.plugboardSettings = "AB";
        messageRequest.key = "AAAA";
        messageRequest.text = "TEST";

        return messageRequest;
    }

}