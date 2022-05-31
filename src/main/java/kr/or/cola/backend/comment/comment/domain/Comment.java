package kr.or.cola.backend.comment.comment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import kr.or.cola.backend.common.BaseTimeEntity;
import kr.or.cola.backend.post.post.domain.Post;
import kr.or.cola.backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Comment(@NotNull User user, @NotNull Post post, @NotNull String content) {
        this.user = user;
        this.post = post;
        this.content = content;
        post.getComments().add(this);
    }

    public void update(@NotBlank String content) {
        this.content = content;
    }

}
