package kr.or.cola.backend.post.presentation.dto;

import javax.validation.constraints.NotNull;
import kr.or.cola.backend.post.domain.Post;
import kr.or.cola.backend.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequestDto {

    @NotNull(message = "Invalid title")
    private String title;

    @NotNull(message = "Invalid content")
    private String content;

}