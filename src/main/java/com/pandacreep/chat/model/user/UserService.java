package com.pandacreep.chat.model.user;

import com.pandacreep.chat.exception.ChatUserExistException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public User save(UserDtoRegister form) throws ChatUserExistException {
        if (isEmailExist(form.getEmail())) {
            String message = "Email " + form.getEmail() + " already exist";
            throw new ChatUserExistException(message);
        }
        var user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(form.getEmail())
                .name(form.getName())
                .password(encoder.encode(form.getPassword()))
                .build();
        return  userRepository.save(user);
    }

    private boolean isEmailExist(String email) {
        var user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

}
