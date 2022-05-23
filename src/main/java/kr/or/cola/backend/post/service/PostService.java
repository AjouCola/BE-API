package kr.or.cola.backend.post.service;


import java.util.List;
import java.util.stream.Collectors;
import kr.or.cola.backend.post.domain.Post;
import kr.or.cola.backend.post.domain.PostRepository;
import kr.or.cola.backend.post.presentation.dto.PostResponseDto;
import kr.or.cola.backend.post.presentation.dto.SimplePostResponseDto;
import kr.or.cola.backend.post.presentation.dto.PostCreateRequestDto;
import kr.or.cola.backend.post.presentation.dto.PostUpdateRequestDto;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public PostResponseDto getPost(Long postId) {
        return new PostResponseDto(initializePostInfo(postId));
    }

    public Long createPost(Long userId, PostCreateRequestDto requestDto) {
        User user = findUserById(userId);
        Post post = Post.builder()
            .title(requestDto.getTitle())
            .content(requestDto.getContent())
            .user(user)
            .build();

        return postRepository.save(post).getId();
    }

    public void updatePost(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        post.update(requestDto.getTitle(), requestDto.getContent());
    }

    public void deletePost(Long postId) {
        Post post = findPostById(postId);
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public List<SimplePostResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
            .map(SimplePostResponseDto::new)
            .collect(Collectors.toList());
    }

    public List<SimplePostResponseDto> findAllPosts(PageRequest pageRequest) {
        return postRepository.findAll(pageRequest).stream()
            .map(SimplePostResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Post initializePostInfo(Long postId) {
        Post post = findPostById(postId);
        Hibernate.initialize(post.getComments());
        return post;
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Post ID: id=" + postId));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid User ID: id=" + userId));
    }

}
