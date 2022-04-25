package com.pandacreep.chat.model.chat;

import com.pandacreep.chat.model.post.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public void addPostToChat(Post post, String chatId) {
        var chat = chatRepository.findById(chatId).get();
        List<Post> posts = chat.getPosts();
        posts.add(post);
        chat.setPosts(posts);
        chatRepository.save(chat);
    }

    public Chat getChat(String chatId) {
        var chat = chatRepository.findById(chatId).get();
        return chat;
    }
}
