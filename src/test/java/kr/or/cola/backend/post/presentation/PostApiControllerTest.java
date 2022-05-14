//package kr.or.cola.backend.post.presentation;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.util.List;
//import kr.or.cola.backend.post.domain.Post;
//import kr.or.cola.backend.post.domain.PostRepository;
//import kr.or.cola.backend.post.presentation.dto.PostSaveRequestDto;
//import kr.or.cola.backend.post.presentation.dto.PostUpdateRequestDto;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PostApiControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @BeforeEach
//    public void setup(){
//        mvc = MockMvcBuilders
//            .webAppContextSetup(context)
//            .apply(springSecurity())
//            .build();
//    }
//
//    @AfterEach
//    public void tearDown() throws Exception {
//        postRepository.deleteAll();
//    }
//
//    @WithMockUser(roles = "USER")
//    @Test
//    public void Posts_등록된다() throws Exception {
//        //given
//        String title = "title";
//        String content = "content";
//        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
//                .title(title)
//                .content(content)
//                .author("author")
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/posts";
//
//        //when
//        mvc.perform(post(url)
//            .contentType(MediaType.APPLICATION_JSON_UTF8)
//            .content(new ObjectMapper().writeValueAsString(requestDto))
//        ).andExpect(status().isOk());
//
//        //then
//        List<Post> all = postRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//    }
//
//    @WithMockUser(roles = "USER")
//    @Test
//    public void Posts_수정된다() throws Exception {
//        //given
//        Post savedPost = postRepository.save(Post.builder()
//            .title("title")
//            .content("content")
//            .author("author")
//            .build());
//
//        Long updateId = savedPost.getId();
//        String expectedTitle = "title";
//        String expectedContent = "content";
//
//        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
//            .title(expectedTitle)
//            .content(expectedContent)
//            .build();
//
//        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
//
//        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
//
//        //when
//        mvc.perform(put(url)
//            .contentType(MediaType.APPLICATION_JSON_UTF8)
//            .content(new ObjectMapper().writeValueAsString(requestDto))
//        ).andExpect(status().isOk());
//
//        //then
//        List<Post> all = postRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
//
//    }
//}
