package com.micro.CommentaireService.controller;

import com.micro.CommentaireService.dto.CommentaireRequest;
import com.micro.CommentaireService.dto.CommentaireResponse;
import com.micro.CommentaireService.service.CommentaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commentaire")
@RequiredArgsConstructor
public class CommentaireController {

    private final CommentaireService commentaireService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCommentaire(@RequestBody CommentaireRequest commentaireRequest){
        commentaireService.createCommentaire(commentaireRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentaireResponse> getAllCommentaires(){
       return commentaireService.getAllCommentaires();

    }


    @PutMapping("/{Id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@PathVariable String Id, @RequestBody CommentaireRequest commentaireRequest) {
        commentaireService.updateCommentaire(Id, commentaireRequest);
    }

    @DeleteMapping("/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String Id) {
        commentaireService.deleteCommentaire(Id);
    }
}
