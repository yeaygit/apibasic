package com.example.apibasic.post.entity;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
@Builder

public class PostEntity {
    private Long postNo;//게시물 식별 번호
    private String writer;//작성자
    private String title;//제목
    private String content;//내용
    private List<String> hashTags;//태그목록
    private LocalDateTime createDate;//작성시간
    private LocalDateTime modifyDate;//수정시간
}
