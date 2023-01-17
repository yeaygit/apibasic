package com.example.apibasic.post.entity;

import com.example.apibasic.post.dto.PostResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode(of = "postNo")
@Builder


//jpa
@Entity
@Table(name = "tbl_post")

public class PostEntity {

    //public static long sequence=1L;//연속된 일련번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")//자동으로 바뀜
    private Long postNo;//게시물 식별 번호

    @Column(nullable = false)
    private String writer;//작성자
    @Column(nullable = false)
    private String title;//제목

    private String content;//내용
    //private List<String> hashTags;//태그목록

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") -> json format은 dto 에서 작성하기
    @CreationTimestamp
    private LocalDateTime createDate;//작성시간
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") -> dto에서 작성하기
    @UpdateTimestamp
    private LocalDateTime modifyDate;//수정시간



}
