package kr.or.cola.backend.post.post.service;


import java.util.List;
import java.util.stream.Collectors;
import kr.or.cola.backend.comment.presentation.dto.CommentResponseDto;
import kr.or.cola.backend.post.post.domain.Post;
import kr.or.cola.backend.post.post.domain.PostRepository;
import kr.or.cola.backend.post.post.domain.PostType;
import kr.or.cola.backend.post.post.presentation.dto.PostCreateOrUpdateRequestDto;
import kr.or.cola.backend.post.post.presentation.dto.PostResponseDto;
import kr.or.cola.backend.post.post.presentation.dto.SimplePostResponseDto;
import kr.or.cola.backend.post.post_tag.domain.PostTag;
import kr.or.cola.backend.post.post_tag.service.PostTagService;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.user.domain.UserRepository;
import kr.or.cola.backend.user.presentation.dto.SimpleUserResponseDto;
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

    private final PostTagService postTagService;

    public PostResponseDto getPost(Long postId) {
        Post post = initializePostInfo(postId);
        return PostResponseDto.builder()
            .postId(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .userInfo(new SimpleUserResponseDto(post.getUser()))
            .comments(post.getComments().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList()))
            .tags(post.getPostTags().stream()
                .map(postTag -> postTag.getTag().getName())
                .collect(Collectors.toList()))
            .createdDate(post.getCreatedDate())
            .modifiedDate(post.getModifiedDate())
            .build();
    }

    public Long createPost(Long userId, PostType postType, PostCreateOrUpdateRequestDto requestDto) {
        User user = findUserById(userId);
        Post post = Post.builder()
            .title(requestDto.getTitle())
            .content(requestDto.getContent())
            .user(user)
            .postType(postType)
            .build();

        List<PostTag> tags = postTagService.setPostTags(post, requestDto.getTags());
        post.addPostTags(tags);

        return postRepository.save(post).getId();
    }

    public void updatePost(Long postId, PostCreateOrUpdateRequestDto requestDto) {
        Post post = findPostById(postId);
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
