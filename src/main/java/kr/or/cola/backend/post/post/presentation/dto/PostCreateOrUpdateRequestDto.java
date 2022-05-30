package kr.or.cola.backend.post.post.presentation.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class PostCreateOrUpdateRequestDto {

    @NotBlank(message = "Invalid title")
    private String title;

    @NotBlank(message = "Invalid content")
    private String content;

    @NotNull(message = "Invalid preview")
    @Size(min = 1, max = 150, message = "Preview is too short or too long")
    private String preview;

    private String thumbnailPath;

    private List<String> tags;

    @Builder
    public PostCreateOrUpdateRequestDto(String title,
                                        String content,
                                        String preview,
                                        String thumbnailPath,
                                        List<String> tags) {
        this.title = title;
        this.content = content;
        this.preview = preview;
        this.thumbnailPath = thumbnailPath;
        this.tags = tags;
    }
}
