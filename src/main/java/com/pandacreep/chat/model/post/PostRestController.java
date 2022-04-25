package com.pandacreep.chat.model.post;

import com.pandacreep.chat.model.chat.Chat;
import com.pandacreep.chat.model.chat.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PostRestController {
    private final ChatService chatService;

    @GetMapping("/posts/{chatId}")
    public ResponseEntity<Chat> getPosts(@PathVariable String chatId) {
        var chat = chatService.getChat(chatId);
        return ResponseEntity.ok().body(chat);
    }
}
