package com.netcracker.hack.sockets;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.netcracker.hack.dto.ChatMessageDTO;
import com.netcracker.hack.dto.NotificationDTO;
import com.netcracker.hack.service.TeamChatService;

@Controller
public class SocketController {

  @Autowired
  private TeamChatService teamChatService;
  
  //@MessageMapping("/notification/{userID}")
  @SendTo("/topic/notifications/{userID}")
  public boolean sendNotification(@DestinationVariable UUID userID , NotificationDTO notification) {
    
    return true;
  }

  @MessageMapping("/team/{teamID}/newMessage")
  @SendTo("/topic/team/{teamID}/chat")
  public ChatMessageDTO updateTeamChat(@DestinationVariable UUID teamID, ChatMessageDTO message) {
    
    
    teamChatService.addMessage(message, teamID);  
    return message;
  }
}
