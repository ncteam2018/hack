package com.netcracker.hack.service.Impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.netcracker.hack.dto.ChatMessageDTO;
import com.netcracker.hack.model.TeamChatMessage;
import com.netcracker.hack.repository.TeamChatRepository;
import com.netcracker.hack.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.hack.service.EventService;
import com.netcracker.hack.service.TeamChatService;

@Service
public class TeamChatServiceImpl implements TeamChatService {

  @Autowired
  private TeamChatRepository teamChatRepository;

  @Autowired
  private TeamRepository teamRepository;
  
  @Autowired
  private EventService eventService;

  @Override
  public void addMessage(ChatMessageDTO message, UUID teamID) {

    message.setDate(Date.valueOf(java.time.LocalDate.now()));
    TeamChatMessage newMessage = new TeamChatMessage(message, teamRepository.findByUuid(teamID));
    teamChatRepository.save(newMessage);
    
    eventService.createNotification("Новое сообщение в чате команды!", null, teamID, null);
  }

  @Override
  public List<ChatMessageDTO> getTeamChat(UUID teamID) {

    return makeTeamChat(teamChatRepository.findAllByTeamUuidOrderByDate(teamID) );
  }

  private List<ChatMessageDTO> makeTeamChat(List<TeamChatMessage> teamChat) {
    
    List<ChatMessageDTO> teamChatDTO = new ArrayList<>();

    teamChat.forEach((TeamChatMessage message) -> {
      teamChatDTO.add(new ChatMessageDTO(message));
    });

    return teamChatDTO;
  }

}
