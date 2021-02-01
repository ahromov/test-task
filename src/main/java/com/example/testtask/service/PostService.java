package com.example.testtask.service;

import com.example.testtask.dto.SearchDTO;
import com.example.testtask.repositories.PostRepository;
import com.example.testtask.entity.Post;
import com.example.testtask.service.exceptions.PostNotExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostService {

    private final PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public List<Post> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Optional<Post> findById(Long postId) {
        return repository.findById(postId);
    }

    public List<Post> findNotApproved() {
        return repository.findNotApproved();
    }

    public List<Post> findApproved() {
        return repository.findApproved();
    }

    public List<Post> findSearchedApproved(SearchDTO searchDTO) {
        return repository.findQueriedApproved(searchDTO.getSearchRequest(), searchDTO.getSearchRequest());
    }

    public void postApprove(Long postId, String action) {
        Optional<Post> post = repository.findById(postId);
        if (post.isPresent() && action.equals("approve")) {
            post.get().setStatus(true);
            repository.save(post.get());
        }
    }

	public Post editPost(Long postId, String title, String body, String action) {
		Optional<Post> post = repository.findById(postId);
		if (post.isPresent() && action.equals("edit")) {
			post.get().setTitle(title);
			post.get().setBody(body);
			return repository.save(post.get());
		}
		throw new PostNotExistsException();
	}
}
