package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

public class PostResponseDTO {

    private String author;
    private String title;
    private String content;
    private List<String> hashTags;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime regDate;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime modifyDate;
    //엔터티를 ResponseDTO 변환 생성자
    public PostResponseDTO(PostEntity entity){
        this.author=entity.getWriter();
        this.content=entity.getContent();
        this.title=entity.getTitle();
        this.regDate=entity.getCreateDate();
        this.hashTags=entity.getHashTags();
        this.modifyDate=entity.getModifyDate();
    }

}
