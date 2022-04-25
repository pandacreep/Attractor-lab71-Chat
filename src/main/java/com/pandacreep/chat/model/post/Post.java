package com.pandacreep.chat.model.post;

import com.pandacreep.chat.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Document("posts")
public class Post {
    @Id
    private String id;
    private String message;
    private LocalDateTime localDateTime;

    @DBRef
    User user;
}
