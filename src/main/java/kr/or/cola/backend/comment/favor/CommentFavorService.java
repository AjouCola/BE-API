package kr.or.cola.backend.comment.favor;

import kr.or.cola.backend.comment.favor.domain.CommentFavor;
import kr.or.cola.backend.comment.favor.domain.CommentFavorRepository;
import kr.or.cola.backend.comment.favor.dto.CommentFavorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.ion.NullValueException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentFavorService {
    private final CommentFavorRepository commentFavorRepository;

    public void createOrUpdateLike(Long userId, Long commentId) {
        CommentFavor commentFavor =  commentFavorRepository
                .findByUserIdAndCommentId(userId, commentId)
                .orElse(new CommentFavor(userId, commentId));
        commentFavorRepository.save(commentFavor);
    }

    public void deleteLike(Long userId, Long commentId) {
        CommentFavor commentFavor = commentFavorRepository.findByUserIdAndCommentId(userId, commentId)
                .orElseThrow(()->new NullValueException("favor is null"));
        commentFavorRepository.delete(commentFavor);
    }

    public CommentFavorResponseDto getCommentLike(Long userId, Long commentId) {
        return new CommentFavorResponseDto(commentFavorRepository.findByUserIdAndCommentId(userId, commentId)
                .orElse(new CommentFavor(userId, commentId)));
    }

    public List<CommentFavorResponseDto> getCommentLikes(Long commentId) {
        return commentFavorRepository.findAllByCommentId(commentId)
                .stream().map(CommentFavorResponseDto::new).collect(Collectors.toList());
    }

    public List<CommentFavorResponseDto> getUserLikes(Long userId) {
        return commentFavorRepository.findAllByUserId(userId)
                .stream().map(CommentFavorResponseDto::new).collect(Collectors.toList());
    }

}
