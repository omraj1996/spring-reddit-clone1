package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Post;
import com.example.model.Subreddit;
import com.example.model.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
}