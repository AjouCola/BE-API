//package kr.or.cola.backend.post.domain;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class PostRepositoryTest {
////@SpringBootTest
//
//    @Autowired
//    PostRepository postRepository;
//
//    @AfterEach
//    public void cleanup() {
//        postRepository.deleteAll();
//    }
//
//    @Test
//    public void 게시글저장_불러오기() {
//        // given
//        String title = "테스트 게시글";
//        String content = "테스트 본문";
//
//        postRepository.save(Post.builder()
//            .title(title)
//            .content(content)
//            .author("sbhong@gmail.com")
//            .build());
//
//        // when
//        List<Post> postList = postRepository.findAll();
//
//        // then
//        Post post = postList.get(0);
//        assertThat(post.getTitle()).isEqualTo(title);
//        assertThat(post.getContent()).isEqualTo(content);
//    }
//
//    @Test
//    public void BaseTimeEntity_등록() {
//       //given
//       LocalDateTime now = LocalDateTime.of(2022,4,5,0,0,0);
//       postRepository.save(Post.builder()
//           .title("title")
//           .content("content")
//           .author("author")
//           .build());
//
//       //when
//        List<Post> postList = postRepository.findAll();
//
//        //then
//        Post post = postList.get(0);
//        System.out.println(">>>>>>>>> createdDate=" + post.getCreatedDate() + ", modifiedDate=" + post.getModifiedDate());
//        assertThat(post.getCreatedDate()).isAfter(now);
//        assertThat(post.getModifiedDate()).isAfter(now);
//    }
//
//}