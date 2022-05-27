package kr.or.cola.backend.like.dto;

import lombok.Getter;

@Getter
public class CULikeRequestDto {
    private Long userId;
    private Long contentId;
    private LikeType likeType;

}
