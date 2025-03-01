package com.example.advanceRestApi.controllers;

import com.example.advanceRestApi.models.User;
import com.example.advanceRestApi.services.UserDoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDoService service;


    public UserController(UserDoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveUser (){
        return service.getUsers();
    }

    @GetMapping("/getUser/{id}")
    public  User getUserById(@PathVariable Integer id){
          User user=service.getUsersById(id);
          if(user == null)
        {
            throw new UserNotFoundException("id:" + id);
        }
         return user;

    }








    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

       User savedUser=service.createUser(user);
       /*URI location= ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(savedUser.getId()).toUri();*/
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().fromPath("/getUser/{id}").buildAndExpand(savedUser.getId()).toUri();
       return ResponseEntity.created(location).build();


    }



    @DeleteMapping("/deleteUser/{id}")
    public  ResponseEntity<String> deleteUserById(@PathVariable Integer id){
        User user=service.getUsersById(id);
        if(user == null)
        {
            throw new UserNotFoundException("user with this id not found:" + id);
        }
        service.deleteUserById(user);
        return ResponseEntity.ok(String.format("User with id : %d has been deleted",id));


    }
    @DeleteMapping("/deleteUserPre/{id}")
    public void deleteUserPre(@PathVariable Integer id){
        service.deleteUserByIdPredicateMethod(id);

    }


}
