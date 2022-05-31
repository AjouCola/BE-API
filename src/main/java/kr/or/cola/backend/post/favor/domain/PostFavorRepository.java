package kr.or.cola.backend.post.favor.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostFavorRepository extends JpaRepository<PostFavor, Long> {
    List<PostFavor> findAllByPostId(Long postId);
    List<PostFavor> findAllByUserId(Long userId);

    Optional<PostFavor> findByUserIdAndPostId(Long userId, Long postId);

    int countPostFavorByPostId(Long postId);
    Boolean existsByUserIdAndPostId(Long userId, Long postId);

}
