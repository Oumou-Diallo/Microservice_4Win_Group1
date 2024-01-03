package com.micro.CommentaireService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;


@Document(value = "commentaire")
@Builder
@Data
@AllArgsConstructor
public class Commentaire {
    @Id
    private String id;
    private String titre;
    private String contenu;
    private LocalDate date;

    public Commentaire() {
        this.date= LocalDate.now();
    }

    public Commentaire(String titre,String contenu) {
        this.titre = titre;
        this.contenu = contenu;
        this.date = LocalDate.now();
    }

}
