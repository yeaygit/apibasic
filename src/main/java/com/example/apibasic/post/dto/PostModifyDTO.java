package com.example.apibasic.post.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostModifyDTO {
    @NotBlank
    @Size(min=2, max=5)//글자수는 2~5사이
    private String title;
    @NotBlank
    @Min(1) @Max(20)
    private String content;
}
