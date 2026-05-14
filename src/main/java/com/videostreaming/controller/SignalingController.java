package com.videostreaming.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "*")
public class SignalingController {

    // Participants will send WebRTC signaling data (offer, answer, candidate) here
    // based on the room they are in.
    
    @MessageMapping("/peer/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public String handleSignalingMessage(@DestinationVariable String roomId, @Payload String message) {
        // In a real application, you would parse the JSON message to ensure
        // it doesn't get echoed back to the sender, but for a basic SFU/Mesh
        // broadcasting it to the topic allows peers to receive offers/answers.
        return message;
    }
}
