package kr.or.cola.backend.favor.post;

import kr.or.cola.backend.favor.comment.domain.CommentFavorPK;
import kr.or.cola.backend.favor.dto.FavorCURequestDto;
import kr.or.cola.backend.favor.dto.FavorResponseDto;
import kr.or.cola.backend.favor.post.domain.PostFavor;
import kr.or.cola.backend.favor.post.domain.PostFavorPK;
import kr.or.cola.backend.favor.post.domain.PostFavorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostFavorService {
    private final PostFavorRepository postFavorRepository;

    public void createOrUpdateLike(FavorCURequestDto requestDto) {
        postFavorRepository
                .findAllById(
                        (Iterable<PostFavorPK>) PostFavorPK.builder()
                                .postId(requestDto.getContentId())
                                .userId(requestDto.getUserId())
                                .build()
                );
    }

    public List<FavorResponseDto> getLikes(Long contentId, Long userId) {
        return getPostLikes(contentId, userId);
    }

    private List<FavorResponseDto> getPostLikes(Long postId, Long userId) {
        List<PostFavor> likes = new ArrayList<>();
        if (postId == null && userId != null) {
            likes = postFavorRepository.findAllByUserId(userId);
        }
        else if (postId != null && userId == null) {
            likes =  postFavorRepository.findAllByPostId(postId);
        }
        else if (postId != null && userId != null) {
            likes =  postFavorRepository.findAllById((Iterable<PostFavorPK>) new CommentFavorPK(userId, postId));
        }
        return likes.stream().map(FavorResponseDto::new).collect(Collectors.toList());
    }

}
