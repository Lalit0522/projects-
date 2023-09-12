package com.example.bloggingApp.service.impl;

import com.example.bloggingApp.entity.Post;
import com.example.bloggingApp.exception.ResourceNotFoundException;
import com.example.bloggingApp.payload.PostDto;
import com.example.bloggingApp.payload.PostResponse;
import com.example.bloggingApp.repository.PostRepository;
import com.example.bloggingApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post created = postRepository.save(post);
        return mapToDto(created);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> all = postRepository.findAll(pageable);
        List<PostDto> contents = all.stream().map(c->mapToDto(c)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageNo(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setTotalPages(all.getTotalPages());
        postResponse.setTotalElements(all.getNumberOfElements());
        postResponse.setLast(all.isLast());
        return postResponse;
    }


    public PostDto updatePost(long id,PostDto postDto){
//        Optional<Post> byId = postRepository.findById(id);
//        Post post = byId.get();

        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post","id",id)
        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updated = postRepository.save(post);
        return mapToDto(updated);
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }
}
