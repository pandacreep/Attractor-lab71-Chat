package com.pandacreep.chat.util;

import com.pandacreep.chat.model.chat.Chat;
import com.pandacreep.chat.model.chat.ChatRepository;
import com.pandacreep.chat.model.post.PostRepository;
import com.pandacreep.chat.model.user.User;
import com.pandacreep.chat.model.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Configuration
@AllArgsConstructor
public class PreloadDatabaseWithData {
    UserRepository userRepository;
    ChatRepository chatRepository;
    PostRepository postRepository;
    PasswordEncoder encoder;

    @Bean
    CommandLineRunner initDatabase() {
        System.out.println("Initialization of MongoDB with initial data");
        userRepository.deleteAll();
        chatRepository.deleteAll();
        postRepository.deleteAll();
        try {
            initUses().run();
            initChats().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CommandLineRunner initUses() {
        return (args) -> Stream.of(users())
                .peek(System.out::println)
                .forEach(userRepository::save);
    }

    private User[] users() {
        return new User[]{
                new User(UUID.randomUUID().toString(),
                        "anna@gmail.com", "Anna",
                        encoder.encode("123")),
                new User(UUID.randomUUID().toString(),
                        "oleg@gmail.com", "Oleg",
                        encoder.encode("123")),
                new User(UUID.randomUUID().toString(),
                        "mike@gmail.com", "Mike",
                        encoder.encode("123"))
        };
    }

    private CommandLineRunner initChats() {
        return (args) -> Stream.of(chats())
                .peek(System.out::println)
                .forEach(chatRepository::save);
    }
    private Chat[] chats() {
        List<User> users = new ArrayList<>();
        users.add(userRepository.findByEmail("anna@gmail.com").get());
        users.add(userRepository.findByEmail("oleg@gmail.com").get());
        users.add(userRepository.findByEmail("mike@gmail.com").get());

        return new Chat[]{
                new Chat("friends", users, new ArrayList<>()),
                new Chat("work", users, new ArrayList<>()),
        };
    }
}
