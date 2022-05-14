package kr.or.cola.backend.comment.presentation.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateOrUpdateRequestDto {

    @NotNull(message = "Invalid content")
    private String content;

}
