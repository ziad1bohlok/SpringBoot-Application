package com.example.advanceRestApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "USER_DETAILS")
public class User {
    public User(Integer id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public User() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Post> getPosts() {
        return Posts;
    }

    public void setPosts(List<Post> Posts) {
        this.Posts = Posts;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message = "Name should have at least 2 characters")
    private String name;

    @Past(message = "Birth Date should be in the past")
    private LocalDate birthday;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> Posts;
}
