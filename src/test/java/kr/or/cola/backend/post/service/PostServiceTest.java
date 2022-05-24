//package kr.or.cola.backend.post.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.stream.Collectors;
//import kr.or.cola.backend.post.domain.Post;
//import kr.or.cola.backend.post.domain.PostRepository;
//import kr.or.cola.backend.post.presentation.dto.SimplePostResponseDto;
//import kr.or.cola.backend.user.UserService;
//import kr.or.cola.backend.user.domain.Role;
//import kr.or.cola.backend.user.domain.User;
//import kr.or.cola.backend.user.domain.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Transactional
//@SpringBootTest
//class PostServiceTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private PostService postService;
//
//    @Test
//    void findAllPostsTest() {
//        // given
//        int TOTAL_POST_COUNT = 20;
//        int PAGE_SIZE = 6;
//        User user = userRepository.save(User.builder()
//            .role(Role.USER)
//            .email("test@test.com")
//            .build());
//        user.setName("test name");
//        for(int i=0; i<TOTAL_POST_COUNT; i++) {
//            postRepository.save(Post.builder()
//                .user(user)
//                .title("title" + i)
//                .content("content" + i)
//                .build()
//            );
//        }
//
//        // when
//        Pageable pageable = PageRequest.of(0, PAGE_SIZE);
//        Page<SimplePostResponseDto> result = postService.findAllPosts(pageable);
//
//        // then
////        System.out.println("PAGE SIZE: " + result.getSize());
////        System.out.println("TOTAL PAGE: " + result.getTotalPages());
////        System.out.println("TOTAL COUNT: " + result.getTotalElements());
////        System.out.println("NEXT: " + result.nextPageable());
////        System.out.println("CONTENTS TITLE");
////        System.out.println(result.getContent().stream()
////            .map(SimplePostResponseDto::getPostId)
////            .collect(Collectors.toList()));
////        System.out.println(result);
//        assertThat(result.getTotalElements()).isEqualTo(TOTAL_POST_COUNT);
//        assertThat(result.getTotalPages()).isEqualTo(TOTAL_POST_COUNT / PAGE_SIZE + 1);
//        assertThat(result.getSize()).isEqualTo(PAGE_SIZE);
//    }
//}