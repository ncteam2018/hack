package com.netcracker.hack.controller.rest;

import com.netcracker.hack.dto.ChatMessageDTO;
import com.netcracker.hack.service.TeamChatService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamChatController {

  @Autowired
  private TeamChatService service;

  @ApiOperation("Returns team chat by uuid")
  @GetMapping("/{uuid}/chat")
  public List<ChatMessageDTO> getTeamChat(@ApiParam(value = "Team's uuid", required = true) @PathVariable(name="uuid") UUID teamID) {
    
    return service.getTeamChat(teamID);
  }
}
