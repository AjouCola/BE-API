package kr.or.cola.backend.post.post.presentation.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateOrUpdateRequestDto {

    @NotBlank(message = "Invalid title")
    private String title;

    @NotBlank(message = "Invalid content")
    private String content;

    private List<String> tags;

    @Builder
    public PostCreateOrUpdateRequestDto(String title, String content, List<String> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }
}
