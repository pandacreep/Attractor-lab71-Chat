package com.pandacreep.chat.model.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private String id;
    private String message;
    private String localDateTime;
    private String email;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .message(post.getMessage())
                .localDateTime(post.getLocalDateTime().toString())
                .email(post.getUser().getEmail())
                .build();
    }

}
