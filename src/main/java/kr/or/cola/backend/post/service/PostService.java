package kr.or.cola.backend.post.service;


import java.util.List;
import java.util.stream.Collectors;
import kr.or.cola.backend.post.domain.Post;
import kr.or.cola.backend.post.domain.PostRepository;
import kr.or.cola.backend.post.presentation.dto.PostListResponseDto;
import kr.or.cola.backend.post.presentation.dto.PostResponseDto;
import kr.or.cola.backend.post.presentation.dto.PostSaveRequestDto;
import kr.or.cola.backend.post.presentation.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->
            new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postRepository.delete(post);
    }

    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
            .map(PostListResponseDto::new)
            .collect(Collectors.toList());
    }

}
