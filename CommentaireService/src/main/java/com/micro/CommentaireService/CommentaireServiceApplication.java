package com.micro.CommentaireService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommentaireServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentaireServiceApplication.class, args);
	}

}
