package com.micro.userservice.controller;

import com.micro.userservice.dto.UserRequest;
import com.micro.userservice.dto.UserResponse;
import com.micro.userservice.model.User;
import com.micro.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserControllerUI {

    private final UserService userService;


    @GetMapping("/display")
    public String getAllUsersUI(Model model) {
        List<UserResponse> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String showAddUserPage(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUserUI(@ModelAttribute("userRequest") @Valid UserRequest user, BindingResult result) {
        if (result.hasErrors()) {
            // Gérer les erreurs de validation, peut-être renvoyer à la page du formulaire avec un message d'erreur.
            return "add-user";
        }

        try {
            userService.createUser(user);
            return "redirect:/api/user/display";
        } catch (Exception e) {
            // Gérer l'exception (par exemple, utilisateur déjà existant) et renvoyer à la page du formulaire avec un message d'erreur.
            return "add-user";
        }
    }



    @GetMapping("/edit/{userId}")
    public String showUpdateUserPage(@PathVariable String userId, Model model) {
        // Récupérer l'utilisateur à modifier en fonction de l'ID
        UserResponse userToUpdate = userService.getUserById(userId);

        // Passer l'utilisateur à la page de confirmation de modification
        model.addAttribute("userToUpdate", userToUpdate);

        return "edit-user";
    }

    @PostMapping("/edit/{userId}")
    public String updateUserUI(@ModelAttribute User user) {
        userService.updateUserUI(user);
        return "redirect:/api/user/display";
    }

    @GetMapping("/delete/{userId}")
    public String showDeleteUserPage(@PathVariable String userId, Model model) {
        // Récupérer l'utilisateur à supprimer en fonction de l'ID
        UserResponse userToDelete = userService.getUserById(userId);

        // Passer l'utilisateur à la page de confirmation de suppression
        model.addAttribute("user", userToDelete);

        return "delete-user";
    }



    @PostMapping("/delete/{userId}")
    public String deleteUserUI(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "redirect:/api/user/display";
    }



}
