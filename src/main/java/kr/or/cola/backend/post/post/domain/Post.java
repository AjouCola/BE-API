package kr.or.cola.backend.post.post.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

import kr.or.cola.backend.comment.comment.domain.Comment;
import kr.or.cola.backend.common.BaseTimeEntity;
import kr.or.cola.backend.post.post_tag.domain.PostTag;
import kr.or.cola.backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Formula;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Column
    private String preview;

    private String thumbnailPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

    @Formula("select count(1) from post_favor where post_favor.post_id=id and status=true")
    private int favorCount;

    @Builder
    public Post(@NonNull String title,
                         String content,
                         String preview,
                         String thumbnailPath,
                         User user,
                         PostType postType) {
        this.title = title;
        this.content = content;
        this.preview = preview;
        this.thumbnailPath = thumbnailPath;
        this.user = user;
        this.postType = postType;
    }

    public void updateContents(String title,
                               String content,
                               String preview,
                               String thumbnailPath) {
        this.title = title;
        this.content = content;
        this.preview = preview == null
            ? this.preview
            : preview;
        this.thumbnailPath = thumbnailPath == null
            ? this.thumbnailPath
            : thumbnailPath;
    }

    public void addPostTags(List<PostTag> postTags) {
        this.postTags.addAll(postTags);
    }

    public void updatePostTags(List<PostTag> updatePostTags) {
        this.postTags.removeIf(postTag ->
            !updatePostTags.contains(postTag)
        );
        this.postTags.addAll(updatePostTags.stream()
            .filter(postTag -> !this.postTags.contains(postTag))
            .collect(Collectors.toList())
        );
    }
}
