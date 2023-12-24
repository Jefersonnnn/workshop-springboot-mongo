package com.jm.workshopmongo.resources;

import com.jm.workshopmongo.domain.Post;
import com.jm.workshopmongo.domain.User;
import com.jm.workshopmongo.dto.UserDTO;
import com.jm.workshopmongo.resources.util.URL;
import com.jm.workshopmongo.services.PostService;
import com.jm.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="title", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="title", defaultValue = "") String title,
            @RequestParam(value="minDate", defaultValue = "") String minDate,
            @RequestParam(value="maxDate", defaultValue = "") String maxDate
    ){
        title = URL.decodeParam(title);
        Instant min = URL.convertInstant(minDate, Instant.now().minus(730,ChronoUnit.DAYS));
        Instant max = URL.convertInstant(maxDate, Instant.now());
        List<Post> list = service.fullSearch(title, min, max);
        return ResponseEntity.ok().body(list);
    }
}
