package org.example.blog_platform_api.comments;

import org.example.blog_platform_api.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment addComment(Long postId, Comment commentData) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        commentData.setPost(post);
        return commentRepository.save(commentData);
    }

    public List<Comment> getAllCommentsForPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public Comment updateComment(Long id, Comment updatedComment) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setCommenterName(updatedComment.getCommenterName());
        comment.setContent(updatedComment.getContent());
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }
}

