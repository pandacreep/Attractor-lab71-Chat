package com.pandacreep.chat.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatSuccessRegisterException extends ChatException {
    public String message;

    public ChatSuccessRegisterException() {
        super();
    }

    public ChatSuccessRegisterException(String message) {
        super(message);
        this.message = message;
    }
}
