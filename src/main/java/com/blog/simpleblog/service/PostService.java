package com.blog.simpleblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.blog.simpleblog.repository.PostJpaRepository;
import com.blog.simpleblog.repository.PostRepository;
import com.blog.simpleblog.vo.Post;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostJpaRepository postJpaRepository;

    public Post getPost(Long id){
        Post post = postJpaRepository.findOneById(id);
        return post;
    }

    public List<Post> getPosts(){
        List<Post> posts = postJpaRepository.findAllByOrderByUpdtDateDesc();

        return posts;
    }

    public List<Post> getPostsOrderByUpdtAsc() {
        List<Post> postsAsc = postJpaRepository.findAllByOrderByUpdtDateAsc();
        return postsAsc;
    }

    public List<Post> getPostsOrderByRegDesc() {
        List<Post> postListDesc = postRepository.findPostOrderByRegDateDesc();
        return postListDesc;
    }

    public List<Post> searchPostByTitle(String query) {
        List<Post> posts = postJpaRepository.findByTitleContainingOrderByUpdtDateDesc(query);
        return posts;
    }

    public List<Post> searchPostByContent(String query) {
        List<Post> posts = postJpaRepository.findByContentContainingOrderByUpdtDateAsc(query);
        return posts;
    }

    public boolean savePost(Post post) {
        Post result = postJpaRepository.save(post);
        boolean isSuccess = true;

        if (result == null){
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean deletePost(Long id){
        Post result = postJpaRepository.findOneById(id);

        if(result == null){
            return false;
        }

        postJpaRepository.deleteById(id);
        return true;
    }

    public boolean updatePost(Post post){
        Post result = postJpaRepository.findOneById(post.getId());

        if (result == null) {
            return false;
        } 
        
        if (!StringUtils.isEmpty(post.getTitle())) {
            result.setTitle(post.getTitle());
        } 
        
        if(!StringUtils.isEmpty(post.getContent())){
            result.setContent(post.getContent());
        }

        postJpaRepository.save(result);

        return true;
    }
}
