package rest.controllers;

import enigma.service.CipheredMessage;
import enigma.service.EnigmaService;
import enigma.service.MessageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("messages")
public class EnigmaRestController {

    @PostMapping
    public CipheredMessage encryptMessage(@RequestBody MessageRequest request) {

        return new EnigmaService().cipherMessage(request);

    }

}
