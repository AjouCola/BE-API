package kr.or.cola.backend.post.post.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByPostType(PostType postType, Pageable pageable);

    Page<Post> findByUserId(Long userId, Pageable pageable);

    Page<Post> findAllByTitleContainsOrContentContains(String titleKeyword, String contentKeyword, Pageable pageable);

}
