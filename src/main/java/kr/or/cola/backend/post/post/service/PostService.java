package kr.or.cola.backend.post.post.service;


import kr.or.cola.backend.post.post.domain.Post;
import kr.or.cola.backend.post.post.domain.PostRepository;
import kr.or.cola.backend.post.post.domain.PostType;
import kr.or.cola.backend.post.post.presentation.dto.PostCreateOrUpdateRequestDto;
import kr.or.cola.backend.post.post.presentation.dto.PostResponseDto;
import kr.or.cola.backend.post.post.presentation.dto.SimplePostResponseDto;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Long createPost(Long userId, PostType postType, PostCreateOrUpdateRequestDto requestDto) {
        User user = findUserById(userId);
        Post post = Post.builder()
            .title(requestDto.getTitle())
            .content(requestDto.getContent())
            .user(user)
            .postType(postType)
            .build();

        // TODO 태그 정보 생성

        return postRepository.save(post).getId();
    }

    public void updatePost(Long id, PostCreateOrUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // TODO 태그 정보 수정

        post.updateContents(requestDto.getTitle(), requestDto.getContent());
    }

    public void deletePost(Long postId) {
        Post post = findPostById(postId);
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public Page<SimplePostResponseDto> findAllPostByPostType(PostType postType, Pageable pageable) {
        return postRepository.findByPostType(postType, pageable)
            .map(SimplePostResponseDto::new);
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
