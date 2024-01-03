package com.micro.CommentaireService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.CommentaireService.dto.CommentaireRequest;
import com.micro.CommentaireService.repository.CommentaireRepo;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class CommentaireServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CommentaireRepo commentaireRepo;

	@DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateCommentaire() throws Exception {

		CommentaireRequest commentaireRequest = getCommentaireRequest();
        String commentaireRequestString = objectMapper.writeValueAsString(commentaireRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/commentaire")
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(commentaireRequestString))
				.andExpect(status().isCreated());
		Assertions.assertTrue(commentaireRepo.findAll().size() == 1 );
	}

	private CommentaireRequest getCommentaireRequest() {
		return CommentaireRequest.builder()
				.titre("La vie est cool")
				.contenu("Oui vous savez cest vrai")
				//.date(LocalDate.parse("2023-12-22"))
				.build();
	}

}
