package com.example.testtask.repositories;

import com.example.testtask.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p where p.status = false order by p.createdAt desc")
    List<Post> findNotApproved();

    @Query(value = "select p from Post p where p.status = true order by p.createdAt desc")
    List<Post> findApproved();

    @Query(value = "select p from Post p where p.title like %:title% or p.body like %:body% order by p.createdAt desc")
    List<Post> findQueriedApproved(@Param("title") String bodyRequest, @Param("body") String titleRequest);

    List<Post> findAllByBodyContainsOrTitleContains(String bodyRequest, String titleRequest);
}
