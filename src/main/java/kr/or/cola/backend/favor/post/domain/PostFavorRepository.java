package kr.or.cola.backend.favor.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostFavorRepository extends JpaRepository<PostFavor, PostFavorPK> {
    List<PostFavor> findAllByPostId(Long postId);
    List<PostFavor> findAllByUserId(Long userId);
}
