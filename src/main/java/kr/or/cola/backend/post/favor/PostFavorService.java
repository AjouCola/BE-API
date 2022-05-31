package kr.or.cola.backend.post.favor;

import kr.or.cola.backend.post.favor.domain.PostFavor;
import kr.or.cola.backend.post.favor.domain.PostFavorRepository;
import kr.or.cola.backend.post.favor.dto.PostFavorInfoResponseDto;
import kr.or.cola.backend.post.favor.dto.PostFavorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.ion.NullValueException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostFavorService {
    private final PostFavorRepository postFavorRepository;

    public void createOrUpdateLike(Long userId, Long postId, Boolean status) {
        PostFavor postFavor =  postFavorRepository
                .findByUserIdAndPostId(userId, postId)
                .orElseGet(() -> new PostFavor(userId, postId, false));
        postFavor.update(status);
        postFavorRepository.save(postFavor);
    }

    public void deleteLike(Long userId, Long postId) {
        PostFavor postFavor = postFavorRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(()->new NullValueException("favor is null"));
        postFavorRepository.delete(postFavor);
    }

    public PostFavorInfoResponseDto getPostFavorInfo(Long userId, Long postId) {
        return PostFavorInfoResponseDto.builder()
                .postId(postId)
                .isFavor(postFavorRepository.findByUserIdAndPostId(userId, postId)
                        .orElseGet(() -> new PostFavor(userId, postId, false))
                        .getStatus())
                .count(postFavorRepository.countPostFavorByPostIdAndStatus(postId, true))
                .build();
    }


}
