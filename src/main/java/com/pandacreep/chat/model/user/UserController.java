package com.pandacreep.chat.model.user;

import com.pandacreep.chat.exception.ChatSuccessRegisterException;
import com.pandacreep.chat.exception.ChatUserExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@Valid UserDtoRegister form,
                               BindingResult validationResult,
                               RedirectAttributes attributes) throws ChatUserExistException, ChatSuccessRegisterException {
        attributes.addFlashAttribute("form", form);
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }
        System.out.println("@PostMapping('/register')");
        User user= userService.save(form);
        throw new ChatSuccessRegisterException(user.getEmail());
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        model.addAttribute("email", principal.getName());
        return "profile";
    }

    @GetMapping("/quit")
    public String showLogout() {
        return "logout-page";
    }

    @ExceptionHandler(ChatUserExistException.class)
    private String handleCustomerExistException(ChatUserExistException ex,
                                                Model model) {
        model.addAttribute("header", "Error");
        model.addAttribute("message", ex.getMessage());
        return "info";
    }

    @ExceptionHandler(ChatSuccessRegisterException.class)
    private String handleSuccessRegisterException(ChatSuccessRegisterException ex,
                                                  Model model) {
        model.addAttribute("header", "Info");
        model.addAttribute("message", "Registration completed successfully");
        return "info";
    }
}
