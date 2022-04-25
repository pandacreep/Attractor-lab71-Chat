package com.pandacreep.chat.model.chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface ChatRepository extends MongoRepository<Chat, String> {}
