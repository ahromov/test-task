package com.example.testtask.service;

import com.example.testtask.dto.SearchDTO;
import com.example.testtask.repositories.PostRepository;
import com.example.testtask.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
		List<Post> posts = repository.findApproved();
		return posts.stream().filter(post -> {
			if (post.getTitle().toLowerCase().contains(searchDTO.getSearchRequest().toLowerCase()) ||
					post.getBody().toLowerCase().contains(searchDTO.getSearchRequest().toLowerCase())){
				return true;
			}
			return false;
		}).collect(Collectors.toList());
    }
}
