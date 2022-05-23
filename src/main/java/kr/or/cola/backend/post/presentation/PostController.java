package kr.or.cola.backend.post.presentation;


import java.util.List;
import kr.or.cola.backend.common.ResponseWithPagination;
import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.post.presentation.dto.PostResponseDto;
import kr.or.cola.backend.post.presentation.dto.PostCreateRequestDto;
import kr.or.cola.backend.post.presentation.dto.PostUpdateRequestDto;
import kr.or.cola.backend.post.presentation.dto.SimplePostResponseDto;
import kr.or.cola.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<Long> createPost(@LoginUser SessionUser user, @RequestBody PostCreateRequestDto requestDto) {
        Long postId = postService.createPost(user.getUserId(), requestDto);
        return ResponseEntity.ok(postId);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto requestDto) {
        postService.updatePost(postId, requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<Page<SimplePostResponseDto>> getPosts(final Pageable pageable) {
        Page<SimplePostResponseDto> posts = postService.findAllPosts(pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto postResponse = postService.getPost(postId);
        return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
