package com.micro.CommentaireService.service;


import com.micro.CommentaireService.dto.CommentaireRequest;
import com.micro.CommentaireService.dto.CommentaireResponse;
import com.micro.CommentaireService.model.Commentaire;
import com.micro.CommentaireService.repository.CommentaireRepo;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentaireService {

    private final CommentaireRepo commentaireRepo;

    public CommentaireResponse getCommentById(String id) {
        // Appel à votre repository ou service pour récupérer l'utilisateur par ID
        Commentaire commentaire = commentaireRepo.findById(id).orElseThrow(() -> new NotFoundException("Commentaire not found"));

        // Conversion de l'entité User en DTO UserResponse (ou autre objet que vous utilisez)
        return mapToDto(commentaire);
    }


    public void createCommentaire(CommentaireRequest commentaireRequest){
        Commentaire commentaire = Commentaire.builder()
                .titre(commentaireRequest.getTitre())
                .contenu(commentaireRequest.getContenu())
                .date(commentaireRequest.getDate())
                .build();

        commentaireRepo.save(commentaire);

        log.info("Comment is save");
    }

    public List<CommentaireResponse> getAllCommentaires() {
       List<Commentaire> commentaires = commentaireRepo.findAll();

       return commentaires.stream().map(this::mapToCommentaireResponse).toList();
    }


    public void updateCommentaire(String commentaireId, CommentaireRequest commentaireRequest) {
        Commentaire existingComment = commentaireRepo.findById(commentaireId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        existingComment.setTitre(commentaireRequest.getTitre());
        existingComment.setContenu(commentaireRequest.getContenu());
        existingComment.setDate(commentaireRequest.getDate());

        commentaireRepo.save(existingComment);

        log.info("Comment is updated");
    }

    public void updateCommentaireUI(Commentaire updatedComment) {
        Commentaire existingComment = commentaireRepo.findById(updatedComment.getId())
                .orElseThrow(() -> new NotFoundException("Commentaire not found"));

        // Mettez à jour les champs de l'utilisateur existant avec les valeurs de l'utilisateur mis à jour
        existingComment.setTitre(updatedComment.getTitre());
        existingComment.setContenu(updatedComment.getContenu());

        commentaireRepo.save(existingComment);
    }

    public void deleteCommentaire(String commentaireId) {
        commentaireRepo.deleteById(commentaireId);

        log.info("Comment is deleted");
    }


    private CommentaireResponse mapToCommentaireResponse(Commentaire commentaire) {
        return CommentaireResponse.builder()
                .id(commentaire.getId())
                .titre(commentaire.getTitre())
                .contenu(commentaire.getContenu())
                .date(commentaire.getDate())
                .build();
    }

    private CommentaireResponse mapToDto(Commentaire commentaire) {
        CommentaireResponse commentaireResponse = new CommentaireResponse();
        commentaireResponse.setId(commentaire.getId());
        commentaireResponse.setTitre(commentaire.getTitre());
        commentaireResponse.setContenu(commentaire.getContenu());
        commentaireResponse.setDate(commentaire.getDate());
        // Autres propriétés à mapper...

        return commentaireResponse;
    }
}
