package com.micro.CommentaireService.repository;

import com.micro.CommentaireService.model.Commentaire;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentaireRepo extends MongoRepository<Commentaire, String> {
}
