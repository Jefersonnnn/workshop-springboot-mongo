package com.jm.workshopmongo.Config;

import com.jm.workshopmongo.domain.Post;
import com.jm.workshopmongo.domain.User;
import com.jm.workshopmongo.repositories.PostRepository;
import com.jm.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

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

        Post post1 = new Post(null, Instant.parse("2023-06-20T19:53:07Z"), "Partiu viajem", "Vou viajar para São Paulo. Abraços!", maria);
        Post post2 = new Post(null, Instant.parse("2022-05-10T19:53:07Z"), "Bom dia!", "Acordei feliz hoje!", maria);
        Post post3 = new Post(null, Instant.parse("2023-05-11T00:52:25Z"), "Que loucura mans", "Hoje fui de base :/", alex);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(post1, post2, post3));

    }
}
