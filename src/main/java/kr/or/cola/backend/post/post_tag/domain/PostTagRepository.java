package kr.or.cola.backend.post.post_tag.domain;

import java.util.List;
import kr.or.cola.backend.post.post.domain.Post;
import kr.or.cola.backend.post.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    List<PostTag> findByPostAndTagIn(Post post, List<Tag> tags);
}
