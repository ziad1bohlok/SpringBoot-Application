package com.example.advanceRestApi.Repository;

import com.example.advanceRestApi.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
