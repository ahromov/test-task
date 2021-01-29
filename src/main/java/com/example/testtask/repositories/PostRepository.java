package com.example.testtask.repositories;

import com.example.testtask.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p where p.status = false order by p.createdAt desc")
    List<Post> findNotApproved();

    @Query(value = "select p from Post p where p.status = true order by p.createdAt desc")
    List<Post> findApproved();
}
