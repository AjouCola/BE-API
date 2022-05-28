package kr.or.cola.backend.post.tag.service;

import java.util.List;
import kr.or.cola.backend.post.tag.domain.Tag;
import kr.or.cola.backend.post.tag.domain.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TagService {

    private final TagRepository tagRepository;

    public Tag createTagIfNotExist(String tagName) {
        Tag tag = new Tag(tagName);

        return tagRepository.findByName(tag.getName())
            .orElseGet(() -> tagRepository.save(tag));
    }

    public List<Tag> findTagsByName(List<String> tagNames) {
        return tagRepository.findByNameIn(tagNames);
    }

}
