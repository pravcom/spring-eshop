package com.akhtyamov.springeshop.controllers;

import com.akhtyamov.springeshop.domain.User;
import com.akhtyamov.springeshop.dto.UserDTO;
import com.akhtyamov.springeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final String notAuth = "You are not authorize";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/new")
    public String newUser(Model model) {
        System.out.println("called method newUser");
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @GetMapping
    public String userList(Model model) {
        List<UserDTO> userDTO = userService.getAll();

        model.addAttribute("users", userDTO );
        return "userList";
    }

    @PostAuthorize("isAuthenticated() and #username == authentication.principal.username")
    @GetMapping("/{name}/roles")
    @ResponseBody
    public String getRoles(@PathVariable("name") String username){
        System.out.println("called method getRoles");
        User byName = userService.findByName(username);
        return byName.getRole().name();
    }


    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model) {
        if (userService.save(userDTO)) {
            return "redirect:/users";
        } else {
            model.addAttribute("user", userDTO);
            return "user";
        }
    }

    @RequestMapping("/test")
    public String test() {
        System.out.println("---------------------TEST------------------------");
        return "test";
    }

    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal){
        if (principal == null){
            throw new RuntimeException(notAuth);
        }

        User user = userService.findByName(principal.getName());

        UserDTO userDTO = UserDTO.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();

        model.addAttribute("user", userDTO);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(UserDTO userDTO, Model model, Principal principal){
        if (principal==null || !Objects.equals(principal.getName(),userDTO.getUsername())){
            throw new RuntimeException(notAuth);
        }
        if (userDTO.getPassword()!=null
            && !userDTO.getPassword().isEmpty()
            && !Objects.equals(userDTO.getPassword(),userDTO.getMatchingPassword()))
        {
            model.addAttribute("users",userDTO);

            return "profile";
        }

        userService.updateProfile(userDTO);
        return "redirect:/users/profile";
    }


}
