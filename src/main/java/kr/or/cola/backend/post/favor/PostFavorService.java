package kr.or.cola.backend.post.favor;

import kr.or.cola.backend.post.favor.domain.PostFavor;
import kr.or.cola.backend.post.favor.domain.PostFavorRepository;
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

    public void createOrUpdateLike(Long userId, Long postId) {
        PostFavor postFavor =  postFavorRepository
                .findByUserIdAndPostId(userId, postId)
                .orElse(new PostFavor(userId, postId));
        postFavorRepository.save(postFavor);
    }

    public void deleteLike(Long userId, Long postId) {
        PostFavor postFavor = postFavorRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(()->new NullValueException("favor is null"));
        postFavorRepository.delete(postFavor);
    }

    public PostFavorResponseDto getPostLikes(Long userId, Long postId) {
        return new PostFavorResponseDto(postFavorRepository.findByUserIdAndPostId(userId, postId)
                .orElse(new PostFavor(userId, postId)));
    }

    public List<PostFavorResponseDto> getPostLikes(Long postId) {
        return postFavorRepository.findAllByPostId(postId)
                .stream().map(PostFavorResponseDto::new).collect(Collectors.toList());
    }

    public List<PostFavorResponseDto> getUserLikes(Long userId) {
        return postFavorRepository.findAllByUserId(userId)
                .stream().map(PostFavorResponseDto::new).collect(Collectors.toList());
    }
}
