package kr.or.cola.backend.comment.service;

import java.util.Objects;
import kr.or.cola.backend.comment.domain.Comment;
import kr.or.cola.backend.comment.domain.CommentRepository;
import kr.or.cola.backend.comment.presentation.dto.CommentCreateOrUpdateRequestDto;
import kr.or.cola.backend.comment.presentation.dto.CommentResponseDto;
import kr.or.cola.backend.post.domain.Post;
import kr.or.cola.backend.post.domain.PostRepository;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public CommentResponseDto getComment(Long commentId) {
        return new CommentResponseDto(initializeCommentInfo(commentId));
    }

    public Comment createComment(Long userId, Long postId, CommentCreateOrUpdateRequestDto requestDto) {
        User user = findUserById(userId);
        Post post = findPostById(postId);
        Comment comment = Comment.builder()
            .user(user)
            .post(post)
            .content(requestDto.getContent())
            .build();
        return commentRepository.save(comment);
    }

    public void updateComment(Long userId, Long commentId, CommentCreateOrUpdateRequestDto requestDto) {
        Comment comment = initializeCommentInfo(commentId);
        isValidUser(comment.getUser().getId(), userId);
        comment.update(requestDto.getContent());
    }

    public void deleteComment(Long userId, Long commentId) {
        Comment comment = initializeCommentInfo(commentId);
        isValidUser(comment.getUser().getId(), userId);
        comment.getPost().getComments().remove(comment);
        commentRepository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    public Comment initializeCommentInfo(Long commentId) {
        Comment comment = findCommentById(commentId);
        Hibernate.initialize(comment.getUser());
        return comment;
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Comment ID: id=" + commentId));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid User ID: id=" + userId));
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Post ID: id=" + postId));
    }

    private void isValidUser(Long requestUserId, Long commentUserId) {
        if (!Objects.equals(requestUserId, commentUserId)) {
            throw new IllegalArgumentException("Unauthenticated User ID: id=" + requestUserId);
        }
    }
}
