package com.pandacreep.chat.model.post;

import com.pandacreep.chat.model.chat.ChatRepository;
import com.pandacreep.chat.model.chat.ChatService;
import com.pandacreep.chat.model.user.User;
import com.pandacreep.chat.model.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ChatService chatService;

    public void addPost(String message, String email, String chatId) {
        User user = userRepository.findByEmail(email).get();
        Post post = Post.builder()
                .id(UUID.randomUUID().toString())
                .message(message)
                .user(user)
                .localDateTime(LocalDateTime.now())
                .build();
        postRepository.save(post);
        chatService.addPostToChat(post, chatId);
    }
}
