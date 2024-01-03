package com.micro.CommentaireService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CommentaireResponse {
    private String id;
    private String titre;
    private String contenu;
    private LocalDate date;

    public CommentaireResponse() {
        this.date= LocalDate.now();
    }

    public CommentaireResponse(String titre,String contenu) {
        this.titre = titre;
        this.contenu = contenu;
        this.date = LocalDate.now();
    }
}
