package com.teamviewer.chatserver.services;


import com.teamviewer.chatserver.model.ChatModel;
import com.teamviewer.chatserver.model.Input.InputChatModel;
import com.teamviewer.chatserver.model.Output.OutputChatModel;
import com.teamviewer.chatserver.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;


@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    private InputChatModel inputChatModel;

    public OutputChatModel chatInput(InputChatModel inputChatModel){
        ChatModel chatModel=new ChatModel(inputChatModel.getRoomId(),inputChatModel.getUserId(),inputChatModel.getChatStr());
        chatRepository.save(chatModel);
        return new OutputChatModel(chatModel.getUserId(),chatModel.getChatStr(),chatModel.getChatCreatedDate());
    }

    public List<OutputChatModel> getAllChat(String roomId) {
        //List<ChatModel> chatModels=chatRepository.findAll();
        List<ChatModel> chatModels=chatRepository.findByRoomId(roomId);
        List<OutputChatModel> outputChatModels = chatModels.stream().map(chatModel -> {
            return new OutputChatModel(chatModel.getUserId(), chatModel.getChatStr(), chatModel.getChatCreatedDate());
        }).collect(Collectors.toList());

        return outputChatModels;
    }
}
