package com.netcracker.hack.service;

import java.util.List;
import java.util.UUID;
import com.netcracker.hack.dto.ChatMessageDTO;

public interface TeamChatService {

  public void addMessage(ChatMessageDTO message, UUID teamID) ;

  public List<ChatMessageDTO> getTeamChat(UUID teamID);
 
}
