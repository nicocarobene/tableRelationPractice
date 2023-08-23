package com.restBiblioteca.comment.controller;

import com.restBiblioteca.comment.Entity.Post;
import com.restBiblioteca.comment.exeption.ResourceNotFoundExeption;
import com.restBiblioteca.comment.respository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    private ResponseEntity<Page<Post>> getAllPost(
            Pageable page
    ){
        return ResponseEntity.ok(postRepository.findAll(page));
    }
    @PostMapping
    private ResponseEntity<Post> createPost(
           @Valid @RequestBody Post post
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(post));
    }
    @PutMapping("/{id}")
    private ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody Post post
    ){
        Optional<Post> updatePost= postRepository.findById(id);
        return updatePost.map(newpost->{
            newpost.setTitle(post.getTitle());
            newpost.setContent(post.getContent());
            newpost.setDescription(post.getDescription());
            return ResponseEntity.ok(postRepository.save(newpost));
        }).orElseThrow(()-> new ResourceNotFoundExeption("Post id "+ id + "is not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(
            @PathVariable Long id
    ){
        Optional<Post> postId= postRepository.findById(id);
        return postId.map(delepost->{
            postRepository.delete(delepost);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElseThrow(()->new ResourceNotFoundExeption("Post id "+ id + "is not found"));
    }
}
