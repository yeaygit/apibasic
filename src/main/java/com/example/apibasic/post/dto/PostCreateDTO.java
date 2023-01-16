package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

public class PostCreateDTO {

    /*
    * Not Null ; null 값일 경우 에러발생
    * Not Empty : 빈문자열일 경우 에러발생
    * Not Blank : null이거나 빈문자열일 경우 에러 발생*/
    @NotBlank
    @Size(min=2, max=5)//글자수는 2~5사이

    private String writer;
    @NotBlank
    @Min(1) @Max(20)
    private String title;
    private String content;
    private List<String> hashTags;

    //postentity로 변환하는 유틸 메서드
    public PostEntity toEntity(){
        return PostEntity.builder()
                .postNo(PostEntity.sequence++)
                .writer(this.writer)
                .content(this.content)
                .title(this.title)
                .hashTags(this.hashTags)
                .createDate(LocalDateTime.now())
                .build();
    }
}
