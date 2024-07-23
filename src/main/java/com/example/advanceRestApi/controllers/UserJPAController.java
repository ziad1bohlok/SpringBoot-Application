package com.example.advanceRestApi.controllers;

import com.example.advanceRestApi.Repository.PostRepository;
import com.example.advanceRestApi.Repository.UserRepository;
import com.example.advanceRestApi.models.User;
import com.example.advanceRestApi.models.Post;
import com.example.advanceRestApi.services.UserDoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAController {
    private UserDoService service;
    private UserRepository jpaService;

    private PostRepository jpaPostService;


    public UserJPAController(UserDoService service, UserRepository jpaService,PostRepository jpaPostService ) {
        this.service = service;
        this.jpaService = jpaService;
        this.jpaPostService=jpaPostService;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveUser (){
        return jpaService.findAll();
    }

    @GetMapping("/jpa/getUser/{id}")
    public Optional<User> getUserById(@PathVariable Integer id){
        Optional<User> user=jpaService.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("id:" + id);
        }
        return user;

    }

    @PostMapping("/jpa/addUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        User savedUser=jpaService.save(user);
       /*URI location= ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(savedUser.getId()).toUri();*/
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().fromPath("/getUser/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();


    }

    @DeleteMapping("/jpa/deleteUser/{id}")
    public  ResponseEntity<String> deleteUserById(@PathVariable Integer id){
        Optional<User> user=jpaService.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("user with this id not found:" + id);
        }
        jpaService.deleteById(id);
        return ResponseEntity.ok(String.format("User with id : %d has been deleted",id));


    }
    @DeleteMapping("/jpa/deleteUserPre/{id}")
    public void deleteUserPre(@PathVariable Integer id){
        jpaService.deleteById(id);

    }

    @GetMapping("/jpa/user/{id}/posts")
    public List<Post> findAllPostsForUserById(@PathVariable Integer id){
        Optional<User> user=jpaService.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("user with this id not found:" + id);
        }
        return user.get().getPosts();

    }

    @PostMapping("/jpa/user/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable Integer id, @RequestBody Post newPost) {
        Optional<User> user = jpaService.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with this ID not found: " + id);
        }


        newPost.setUser(user.get());


        Post savedPost = jpaPostService.save(newPost);

        // Build the location URI for the created post
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand(savedPost.getId())
                .toUri();


        return ResponseEntity.created(location).build();
    }
}
