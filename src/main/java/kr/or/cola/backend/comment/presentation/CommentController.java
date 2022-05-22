package kr.or.cola.backend.comment.presentation;

import kr.or.cola.backend.comment.presentation.dto.CommentCreateOrUpdateRequestDto;
import kr.or.cola.backend.comment.presentation.dto.CommentResponseDto;
import kr.or.cola.backend.comment.service.CommentService;
import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@LoginUser SessionUser user,
        @PathVariable Long postId, @RequestBody CommentCreateOrUpdateRequestDto requestDto) {
        CommentResponseDto responseDto = new CommentResponseDto(
            commentService.createComment(user.getUserId(), postId, requestDto));
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@LoginUser SessionUser user,
        @PathVariable Long commentId, @RequestBody CommentCreateOrUpdateRequestDto requestDto) {
        commentService.updateComment(user.getUserId(), commentId, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@LoginUser SessionUser user, @PathVariable Long commentId) {
        commentService.deleteComment(user.getUserId(), commentId);
        return ResponseEntity.ok().build();
    }

}
