package com.example.advanceRestApi.Repository;

import com.example.advanceRestApi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
