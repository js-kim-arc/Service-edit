package hello.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter @Column(nullable = false) String title;
    @Setter @Column(nullable = false, length = 10_000) String content;

    @Setter @Column private String hashtag;


    @CreatedDate @Column(nullable = false) LocalDateTime createdAt;
    @CreatedBy @Column(nullable = false )String createdBy;
    @LastModifiedBy @Column(nullable = false) String modifiedBy;
    @LastModifiedDate @Column(nullable = false) LocalDateTime modifiedAt;

    //하이버네이트를 쓴다면 필수
    public Article() {

    }

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    //이게 팩토리 메소드임
    public static Article of(String title, String content, String hashtag) {
        return new Article(title,content,hashtag);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
