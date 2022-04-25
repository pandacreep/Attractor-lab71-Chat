package com.pandacreep.chat.model.chat;

import com.pandacreep.chat.model.post.PostService;
import com.pandacreep.chat.model.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class ChatController {
    private final PostService postService;

    @GetMapping("/chat-friends")
    public String showChatFriends(Model model, Principal principal) {
        model.addAttribute("posts", new ArrayList<>());
        model.addAttribute("email", principal.getName());
        model.addAttribute("chatId", "friends");
        return "chat-friends";
    }

    @GetMapping("/chat-work")
    public String showChatWork(Model model, Principal principal) {
        model.addAttribute("posts", new ArrayList<>());
        model.addAttribute("email", principal.getName());
        model.addAttribute("chatId", "work");
        return "chat-work";
    }

    @PostMapping("/add-post")
    public String addPost(@RequestParam String message,
                          @RequestParam String email,
                          @RequestParam String chatId) {
        postService.addPost(message, email, chatId);
        if (chatId.equalsIgnoreCase("friends")) {
            return "redirect:/chat-friends";
        }
        return "redirect:/chat-work";
    }

}
