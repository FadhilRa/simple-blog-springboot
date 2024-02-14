package com.blog.simpleblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.simpleblog.repository.CommentJpaRepository;
import com.blog.simpleblog.vo.Comment;

@Service
public class CommentService {

    @Autowired
    CommentJpaRepository commentJpaRepository;

    public boolean saveComment(Comment comment){
        Comment result = commentJpaRepository.save(comment);
        boolean isSuccess = true;

        if (result == null) {
            isSuccess = false;
        }

        return isSuccess;
    }

    public List<Comment> getCommentByPostId(Long postId){
        List<Comment> comments = commentJpaRepository.findAllByPostIdOrderByRegDateDesc(postId);
        return comments;
    }

    public Comment getComment(Long id) {
        return commentJpaRepository.findById(id);
    }

    public boolean deleteComment(Long id) {
        Comment result = commentJpaRepository.findById(id);

        if(result == null){
            return false;
        }
        
        commentJpaRepository.deleteById(id);
        return true;
    }

    public List<Comment> searchComments(Long postId, String query) {
        return commentJpaRepository.findByPostIdAndCommentContainingOrderByRegDateDesc(postId, query);
    }
}
