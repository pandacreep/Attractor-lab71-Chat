package com.pandacreep.chat.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatUserExistException extends ChatException {
    private String message;

    public ChatUserExistException() {
        super();
    }

    public ChatUserExistException(String message) {
        super(message);
        this.message = message;
    }
}
