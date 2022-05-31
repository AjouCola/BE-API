package kr.or.cola.backend.post.post.presentation;


import javax.validation.Valid;
import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.post.post.domain.PostType;
import kr.or.cola.backend.post.post.presentation.dto.PostResponseDto;
import kr.or.cola.backend.post.post.presentation.dto.PostCreateOrUpdateRequestDto;
import kr.or.cola.backend.post.post.presentation.dto.SimplePostResponseDto;
import kr.or.cola.backend.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/{postType}")
    public ResponseEntity<Long> createPost(
            @LoginUser SessionUser user,
            @PathVariable PostType postType,
            @Valid @RequestBody PostCreateOrUpdateRequestDto requestDto) {
        Long postId = postService.createPost(user.getUserId(), postType, requestDto);
        return ResponseEntity.ok(postId);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(
            @PathVariable Long postId,
            @Valid @RequestBody PostCreateOrUpdateRequestDto requestDto) {
        postService.updatePost(postId, requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<Page<SimplePostResponseDto>> getPosts(
            @LoginUser SessionUser sessionUser,
            @RequestParam(value = "category") PostType postType,
            @PageableDefault(
                size = 12,
                sort = "favorCount",
                direction = Sort.Direction.DESC) Pageable pageable) {
        Page<SimplePostResponseDto> posts =
            postService.findAllPostByPostType(sessionUser.getUserId(), postType, pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(
            @LoginUser SessionUser sessionUser,
            @PathVariable Long postId) {
        PostResponseDto postResponse = postService.getPost(sessionUser, postId);
        return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
