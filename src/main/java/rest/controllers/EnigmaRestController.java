package rest.controllers;

import enigma.service.CipheredMessage;
import enigma.service.EnigmaService;
import enigma.service.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cipher")
public class EnigmaRestController {

    private final EnigmaService enigmaService;

    @Autowired
    public EnigmaRestController(EnigmaService enigmaService) {
        this.enigmaService = enigmaService;
    }

    @PostMapping
    public CipheredMessage cipherMessage(@RequestBody MessageRequest request) {

        return enigmaService.cipherMessage(request);
    }
    
    @GetMapping
    public String get() {
        return "Enigma Machine Service";
    }

}
