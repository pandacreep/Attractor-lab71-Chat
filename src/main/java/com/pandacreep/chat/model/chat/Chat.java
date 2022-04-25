package com.pandacreep.chat.model.chat;

import com.pandacreep.chat.model.post.Post;
import com.pandacreep.chat.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document("chats")
public class Chat {
    @Id
    private String id;

    @DBRef
    List<User> users;

    @DBRef
    List<Post> posts;
}
