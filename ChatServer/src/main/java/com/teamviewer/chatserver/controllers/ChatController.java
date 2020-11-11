package com.teamviewer.chatserver.controllers;

import com.teamviewer.chatserver.model.ChatModel;
import com.teamviewer.chatserver.model.Input.InputChatModel;
import com.teamviewer.chatserver.model.Output.OutputChatModel;
import com.teamviewer.chatserver.services.ChatService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
//@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
   
    @GetMapping("/chat/{roomId}")
    public List<OutputChatModel> getChat(@PathVariable String roomId){
        List<OutputChatModel> outputChatModels = chatService.getAllChat(roomId);
        log.debug("get chat ok");
        return outputChatModels;
    }

    @MessageMapping("/{roomId}")
    @SendTo("/topic/{roomId}")
    public OutputChatModel sendChat(@RequestBody InputChatModel inputChatModel) throws Exception{
        //OutputChatModel outputChatModel=chatService.chatInput(inputChatModel);
        System.out.println(inputChatModel.getChatStr());
        log.info(inputChatModel.getChatStr());
        return chatService.chatInput(inputChatModel);
    }

}
