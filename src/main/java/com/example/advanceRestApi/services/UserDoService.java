package com.example.advanceRestApi.services;

import com.example.advanceRestApi.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDoService {

    private static List<User> Users =new ArrayList<>();
    private static Integer idCounter=0;



    static {
        Users.add(new User(++idCounter,"Ziad", LocalDate.now().minusYears(30)));
        Users.add(new User(++idCounter,"aya", LocalDate.now().minusYears(30)));
        Users.add(new User(++idCounter,"ali", LocalDate.now().minusYears(30)));

    }

    public List<User> getUsers(){
        return Users;
    }

    public User getUsersById(Integer id){
        return    Users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);

    }

    public User createUser(User user) {
        user.setId(++idCounter);
        Users.add(user);
        return user;
    }

    public void deleteUserById(User user) {
        Users.remove(user);
    }


    public void deleteUserByIdPredicateMethod(int id){
        Predicate<? super User> predicate= user -> user.getId().equals(id);
        Users.removeIf(predicate);
    }
}
