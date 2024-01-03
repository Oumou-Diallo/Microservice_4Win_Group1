package com.micro.CommentaireService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CommentaireRequest {
    private String titre;
    private String contenu;
    private LocalDate date;

    public CommentaireRequest() {
        this.date= LocalDate.now();
    }

    public CommentaireRequest(String titre,String contenu) {
        this.titre = titre;
        this.contenu = contenu;
        this.date = LocalDate.now();
    }
}
