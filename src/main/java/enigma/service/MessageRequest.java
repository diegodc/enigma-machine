package enigma.service;

/**
 * A data structure that holds all the necessary data for encoding a message.
 *
 * @author diegodc 2017-04-10.
 */
public class MessageRequest {

    public EnigmaModel enigmaModel;
    public String wheels = "";
    public ReflectorModel reflector;
    public String ringSettings = "";
    public String plugboard = "";
    public String key = "";
    public String message = "";

}
