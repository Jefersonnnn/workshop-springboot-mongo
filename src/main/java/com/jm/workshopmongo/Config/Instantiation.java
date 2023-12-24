package com.jm.workshopmongo.Config;

import com.jm.workshopmongo.domain.Post;
import com.jm.workshopmongo.domain.User;
import com.jm.workshopmongo.dto.AuthorDTO;
import com.jm.workshopmongo.dto.CommentDTO;
import com.jm.workshopmongo.repositories.PostRepository;
import com.jm.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, Instant.parse("2023-06-20T19:53:07Z"), "Partiu viajem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, Instant.parse("2022-05-10T08:10:07Z"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));
        Post post3 = new Post(null, Instant.parse("2023-05-11T00:52:25Z"), "Que loucura mans", "Hoje fui de base :/", new AuthorDTO(alex));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", Instant.parse("2023-06-20T20:55:01Z"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", Instant.parse("2023-06-20T20:22:01Z"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", Instant.parse("2022-05-10T09:30:05Z"), new AuthorDTO(alex));
        CommentDTO c4 = new CommentDTO("PORMIM!", Instant.parse("2023-05-11T05:52:25Z"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);
        post3.getComments().add(c4);

        postRepository.saveAll(Arrays.asList(post1, post2, post3));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        alex.getPosts().add(post3);
        userRepository.saveAll(Arrays.asList(maria, alex));

    }
}
