package kr.or.cola.backend.favor.comment;

import kr.or.cola.backend.favor.comment.domain.CommentFavor;
import kr.or.cola.backend.favor.comment.domain.CommentFavorPK;
import kr.or.cola.backend.favor.comment.domain.CommentFavorRepository;
import kr.or.cola.backend.favor.dto.FavorCURequestDto;
import kr.or.cola.backend.favor.dto.FavorResponseDto;
import kr.or.cola.backend.favor.post.domain.PostFavorPK;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentFavorService {
    private final CommentFavorRepository commentFavorRepository;

    public void createOrUpdateLike(FavorCURequestDto requestDto) {
        commentFavorRepository
                .findAllById(
                        (Iterable<CommentFavorPK>) CommentFavorPK.builder()
                                .commentId(requestDto.getContentId())
                                .userId(requestDto.getUserId())
                                .build()
                );
    }

    public List<FavorResponseDto> getLikes(Long commentId, Long userId) {
        return getCommentLikes(commentId, userId);
    }

    private List<FavorResponseDto> getCommentLikes(Long commentId, Long userId) {
        List<CommentFavor> likes = new ArrayList<>();
        if (commentId == null && userId != null) {
            likes = commentFavorRepository.findAllByUserId(userId);
        }
        else if (commentId != null && userId == null) {
            likes =  commentFavorRepository.findAllByCommentId(commentId);
        }
        else if (commentId != null && userId != null) {
            likes =  commentFavorRepository.findAllById((Iterable<CommentFavorPK>) new CommentFavorPK(userId, commentId));
        }
        return likes.stream().map(FavorResponseDto::new).collect(Collectors.toList());
    }

}
