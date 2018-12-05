package com.netcracker.hack.sockets;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


    @MessageMapping("/check")
    @SendTo("/topic/notifications")
    public boolean greeting() throws Exception {
        return true;
    }

}
