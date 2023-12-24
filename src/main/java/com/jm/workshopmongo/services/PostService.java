package com.jm.workshopmongo.services;

import com.jm.workshopmongo.domain.Post;
import com.jm.workshopmongo.repositories.PostRepository;
import com.jm.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> findAll(){
        return repository.findAll();
    }

    public Post findById(String id){
        Optional<Post> post = repository.findById(id);
        if (post.isEmpty()){
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return post.get();
    }

    public List<Post> findByTitle(String text){
        return repository.findByTitle(text);
    }

}
