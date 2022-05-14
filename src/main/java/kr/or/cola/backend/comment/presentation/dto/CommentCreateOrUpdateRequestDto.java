package kr.or.cola.backend.comment.presentation.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentCreateOrUpdateRequestDto {

    @NotNull(message = "Invalid content")
    private String content;

}
