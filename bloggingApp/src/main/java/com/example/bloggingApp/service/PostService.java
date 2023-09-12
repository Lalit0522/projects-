package com.example.bloggingApp.service;

import com.example.bloggingApp.payload.PostDto;
import com.example.bloggingApp.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo,int pageSize,String sortBy,String sortDir);

     PostDto updatePost(long id, PostDto postDto);
}
