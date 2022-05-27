package kr.or.cola.backend.like;

import kr.or.cola.backend.like.domain.CommentLikeRepository;
import kr.or.cola.backend.like.domain.PostLikeRepository;
import kr.or.cola.backend.like.domain.SolutionLikeRepository;
import kr.or.cola.backend.like.domain.pk.LikeCommentPK;
import kr.or.cola.backend.like.domain.pk.LikePostPK;
import kr.or.cola.backend.like.domain.pk.LikeSolutionPK;
import kr.or.cola.backend.like.dto.CULikeRequestDto;
import kr.or.cola.backend.like.dto.LikeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final PostLikeRepository postLikeRepository;
    private final SolutionLikeRepository solutionLikeRepository;
    private final CommentLikeRepository commentLikeRepository;

    public void createOrUpdateLike(CULikeRequestDto requestDto) {
        LikeType type = requestDto.getLikeType();
        if (type == LikeType.POST) {
            postLikeRepository.findAllById((Iterable<LikePostPK>) LikePostPK.builder().postId(requestDto.getContentId()).userId(requestDto.getUserId()).build());
        }
        else if (type == LikeType.COMMENT) {
            commentLikeRepository.findAllById((Iterable<LikeCommentPK>) LikePostPK.builder().postId(requestDto.getContentId()).userId(requestDto.getUserId()).build());
        }
        else if (type == LikeType.SOLUTION) {
            solutionLikeRepository.findAllById((Iterable<LikeSolutionPK>) LikePostPK.builder().postId(requestDto.getContentId()).userId(requestDto.getUserId()).build());
        }
        else {

        }
    }

}
