package org.example.blog_platform_api.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Post> createPost(@PathVariable Long userId, @RequestBody Post postData) {
        Post post = postService.createPost(userId, postData);
        return ResponseEntity.status(201).body(post);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getAllPostsByUser(userId));
    }

    @GetMapping("")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
        return ResponseEntity.ok(postService.updatePost(postId, updatedPost));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam("q") String query) {
        return ResponseEntity.ok(postService.searchPosts(query));
    }
}
