package kr.or.cola.backend.post.presentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import kr.or.cola.backend.post.domain.Post;
import kr.or.cola.backend.post.domain.PostRepository;
import kr.or.cola.backend.post.service.PostService;
import kr.or.cola.backend.user.domain.Role;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @WithMockUser(roles = "USER")
    @Test
    void getPostsTest() throws Exception {
        // given
        int TOTAL_POST_COUNT = 20;
        User user = userRepository.save(User.builder()
            .role(Role.USER)
            .email("test@test.com")
            .build());
        for(int i=0; i<TOTAL_POST_COUNT; i++) {
            postRepository.save(Post.builder()
                .user(user)
                .title("title" + i)
                .content("content" + i)
                .build()
            );
        }
        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        info.add("size", "6");
        info.add("page", "0");

        mvc.perform(get(url)
                .params(info))
            .andExpect(status().isOk())
            .andDo(print());
    }
}