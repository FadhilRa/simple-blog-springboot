package com.blog.simpleblog.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.simpleblog.vo.Comment;

public interface CommentJpaRepository extends JpaRepository<Comment, Serializable>{
    List<Comment> findAllByPostIdOrderByRegDateDesc(Long postId);
    Comment findById(Long id);
    List<Comment> findByPostIdAndCommentContainingOrderByRegDateDesc(Long postId, String query);
}
