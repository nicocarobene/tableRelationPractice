package com.restBiblioteca.comment.controller;

import com.restBiblioteca.comment.Entity.Comment;
import com.restBiblioteca.comment.Entity.Post;
import com.restBiblioteca.comment.exeption.ResourceNotFoundExeption;
import com.restBiblioteca.comment.respository.CommentsRepository;
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
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(
            @PathVariable Long id
    ){
        Optional<Comment> getComment= commentsRepository.findById(id);
        return getComment.map(ResponseEntity::ok).orElseThrow(()-> new ResourceNotFoundExeption("Comment id "+ id + "is not found"));
    }
    @GetMapping
    private ResponseEntity<Page<Comment>> getAllCommentByPost(
            @RequestParam(required = false, defaultValue = "0") Long postId,
            Pageable page
    ){
        System.out.println("queryParam "+ postId);
        return ResponseEntity.ok(commentsRepository.findByPostId(postId,page));
    }
    @PostMapping
    private ResponseEntity<Comment> createComment(
            @RequestParam long postId,
            @Valid @RequestBody Comment newcomment
    ){
        Optional<Post> getPost= postRepository.findById(postId);
        return getPost.map(post -> {
            newcomment.setPost(post);
            return ResponseEntity.ok(commentsRepository.save(newcomment));
        }).orElseThrow(()-> new ResourceNotFoundExeption("Post id "+ postId + "is not found"));
    }
    @PutMapping("/{id}")
    private ResponseEntity<Comment> updateComment(
            @PathVariable Long id,
            @Valid @RequestBody Comment comment
    ){
        Optional<Comment> updateComment= commentsRepository.findById(id);
        return updateComment.map(newComment->{
            newComment.setComment(comment.getComment());
            return ResponseEntity.ok(commentsRepository.save(newComment));
        }).orElseThrow(()-> new ResourceNotFoundExeption("Comment id "+ id + "is not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(
            @PathVariable Long id
    ){
        Optional<Comment> commentId= commentsRepository.findById(id);
        return commentId.map(deleteComment->{
            commentsRepository.delete(deleteComment);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElseThrow(()->new ResourceNotFoundExeption("Comment id "+ id + "is not found"));
    }
}
