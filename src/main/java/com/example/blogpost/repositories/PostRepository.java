package com.example.blogpost.repositories;



import com.example.blogpost.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Override
    Optional<Post> findById(Long aLong);
}
