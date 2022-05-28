package kr.or.cola.backend.post.post_tag.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.or.cola.backend.post.post.domain.Post;
import kr.or.cola.backend.post.post_tag.domain.PostTag;
import kr.or.cola.backend.post.post_tag.domain.PostTagRepository;
import kr.or.cola.backend.post.tag.domain.Tag;
import kr.or.cola.backend.post.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostTagService {

    private final TagService tagService;
    private final PostTagRepository postTagRepository;

    public List<PostTag> setPostTags(Post post, List<String> tagNames) {
        List<Tag> tags = tagNames.stream()
            .map(tagService::createTagIfNotExist)
            .collect(Collectors.toList());
        return tags.stream()
            .map(tag ->
                postTagRepository.save(PostTag.builder()
                    .post(post)
                    .tag(tag)
                    .build()))
            .collect(Collectors.toList());
    }

//    public List<PostTag> updatePostTags(Post post, List<String> tagNames) {
//
//    }

}
