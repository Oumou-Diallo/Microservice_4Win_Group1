package com.micro.CommentaireService.controller;


import com.micro.CommentaireService.dto.CommentaireRequest;
import com.micro.CommentaireService.dto.CommentaireResponse;
import com.micro.CommentaireService.model.Commentaire;
import com.micro.CommentaireService.service.CommentaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/commentaire")
@RequiredArgsConstructor
public class CommentaireControllerUI {

    private final CommentaireService commentaireService;


    @GetMapping("/display")
    public String getAllCommentairesUI(Model model) {
        List<CommentaireResponse> commentaires = commentaireService.getAllCommentaires();
        model.addAttribute("commentaires", commentaires);
        return "commentaires";
    }

    @GetMapping("/add")
    public String showAddCommentairePage(Model model) {
        model.addAttribute("commentaireRequest", new CommentaireRequest());
        return "add-comment";
    }

    @PostMapping("/add")
    public String addCommentaireUI(@ModelAttribute("commentaireRequest") @Valid CommentaireRequest commentaireRequest, BindingResult result) {
        if (result.hasErrors()) {
            // Gérer les erreurs de validation, peut-être renvoyer à la page du formulaire avec un message d'erreur.
            return "add-comment";
        }

        try {
            commentaireService.createCommentaire(commentaireRequest);
            return "redirect:/api/commentaire/display";
        } catch (Exception e) {
            // Gérer l'exception (par exemple, utilisateur déjà existant) et renvoyer à la page du formulaire avec un message d'erreur.
            return "add-comment";
        }
    }



    @GetMapping("/edit/{id}")
    public String showUpdateCommentairePage(@PathVariable String id, Model model) {
        // Récupérer l'utilisateur à modifier en fonction de l'ID
        CommentaireResponse commentToUpdate = commentaireService.getCommentById(id);

        // Passer l'utilisateur à la page de confirmation de modification
        model.addAttribute("commentToUpdate", commentToUpdate);

        return "edit-comment";
    }

    @PostMapping("/edit/{id}")
    public String updateCommentaireUI(@ModelAttribute Commentaire commentaire) {
        commentaireService.updateCommentaireUI(commentaire);
        return "redirect:/api/commentaire/display";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteCommentairePage(@PathVariable String id, Model model) {
        // Récupérer l'utilisateur à supprimer en fonction de l'ID
        CommentaireResponse commentToDelete = commentaireService.getCommentById(id);

        // Passer l'utilisateur à la page de confirmation de suppression
        model.addAttribute("comment", commentToDelete);

        return "delete-comment";
    }



    @PostMapping("/delete/{id}")
    public String deleteCommentaireUI(@PathVariable String id) {
        commentaireService.deleteCommentaire(id);
        return "redirect:/api/commentaire/display";
    }



}

